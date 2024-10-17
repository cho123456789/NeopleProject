package com.example.myapplication

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresExtension
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.data.remote.dto.BufferEquipment
import com.data.remote.dto.Item
import com.example.myapplication.ui.Screen.BuffEquipmentScreen
import com.example.myapplication.ui.Screen.CharacterSearchScreen
import com.example.myapplication.ui.Screen.EquipmentScreen
import com.example.myapplication.ui.Screen.HomeScreen
import com.example.myapplication.ui.Screen.MainScreen
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            // Pass the navController down to the CharacterSearchScreen
                            //HomeScreen(navController = navController)
                            CharacterSearchScreen(navController = navController)
                        }
                        composable("장착장비"){
                            EquipmentScreen(
                                navController = navController
                            )
                        }
                        composable("버프강화"){
                            BuffEquipmentScreen(
                                navController = navController
                            )
                        }
                        composable("크리쳐") {
                            MainScreen(navController = navController)
                        }
                    }
                    //BottomNavigationBar(navController)
                }
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    @Composable
    fun MyApp() {
        val navController = rememberNavController()
        //MainScreen()
        HomeScreen(navController)
    }
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Preview(showBackground = true)
@Composable
   fun PreviewMainContainer() {
        MaterialTheme{
            MyApp()
        }
    }
}
