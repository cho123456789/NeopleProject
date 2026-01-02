package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresExtension
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.ui.Screen.AvatarScreen
import com.example.myapplication.ui.Screen.CharacterSearchScreen
import com.example.myapplication.ui.Screen.EquipmentScreen
import com.example.myapplication.ui.Screen.HomeScreen
import com.example.myapplication.ui.Screen.MainScreen
import com.example.myapplication.ui.Screen.MistAssimilationScreen
import com.example.myapplication.ui.Screen.StatusScreen
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @SuppressLint("UnusedContentLambdaTargetStateParameter")
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
                            HomeScreen(navController = navController)
                            //CharacterSearchScreen(navController = navController)
                        }
                        composable("장착장비",
                            enterTransition = { EnterTransition.None },
                            exitTransition = { ExitTransition.None },
                            popEnterTransition = { EnterTransition.None },
                            popExitTransition = { ExitTransition.None }
                        ){
                                EquipmentScreen(
                                    navController = navController
                                )
                        }
                        composable(
                            route = "안개융화/{serverId}/{characterId}",
                            arguments = listOf(
                                navArgument("serverId") { type = NavType.StringType },
                                navArgument("characterId") { type = NavType.StringType }
                            ),
                            enterTransition = { EnterTransition.None },
                            exitTransition = { ExitTransition.None },
                            popEnterTransition = { EnterTransition.None },
                            popExitTransition = { ExitTransition.None }
                        ) { backStackEntry ->
                            val serverId = backStackEntry.arguments?.getString("serverId") ?: ""
                            val characterId =
                                backStackEntry.arguments?.getString("characterId") ?: ""
                            MistAssimilationScreen(
                                navController = navController,
                                serverId = serverId,
                                characterId = characterId
                            )
                        }
                        composable("크리쳐") {
                            MainScreen(navController = navController)
                        }
                        composable("캐릭터검색") {
                            CharacterSearchScreen(navController = navController)
                        }
                        composable("아바타") {
                            AvatarScreen(navController = navController)
                        }
                        composable(
                            route = "능력치/{serverId}/{characterId}",
                            arguments = listOf(
                                navArgument("serverId") { type = NavType.StringType },
                                navArgument("characterId") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val serverId = backStackEntry.arguments?.getString("serverId") ?: ""
                            val characterId =
                                backStackEntry.arguments?.getString("characterId") ?: ""
                            StatusScreen(
                                navController = navController,
                                serverId = serverId,
                                characterId = characterId
                            )
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
