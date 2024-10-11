package com.example.myapplication.ui.Screen

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

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