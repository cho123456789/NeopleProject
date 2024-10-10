package com.example.myapplication.ui.theme

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.data.remote.dto.Item
import com.example.myapplication.ui.CharacterSearchScreen
import com.google.gson.Gson

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun MainScreen() {

}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route
    val items = listOf("home", "equipment", "notifications","back")
    BottomNavigation {
        items.forEach { screen ->
            BottomNavigationItem(
                label = { Text(screen.capitalize()) },
                icon = { /* 아이콘 추가 */ },
                selected = currentRoute == screen,
                onClick = {
                    navController.navigate(screen) {
                        // 중복된 항목 클릭 시 이전 상태로 돌아가지 않도록 설정
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}