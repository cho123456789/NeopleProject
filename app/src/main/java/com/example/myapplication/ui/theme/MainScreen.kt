package com.example.myapplication.ui.theme

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.data.remote.dto.Item
import com.example.myapplication.ui.CharacterSearchScreen
import com.google.gson.Gson

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun MainScreen() {
    // Create the NavController at the higher level
    val navController = rememberNavController()

    // Place the NavHost here
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            // Pass the navController down to the CharacterSearchScreen
            CharacterSearchScreen(navController = navController)
        }
        composable(
            "equipment/{equipmentListJson}",
            arguments = listOf(navArgument("equipmentListJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val equipmentListJson = backStackEntry.arguments?.getString("equipmentListJson")
            val gson = Gson()
            val equipmentList = gson.fromJson(equipmentListJson, Array<Item>::class.java).toList()
            EquipmentScreen(equipmentList = equipmentList)
        }
    }
}