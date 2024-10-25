package com.example.myapplication.ui.Screen

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.viewmodel.BufferEquipmentViewModel
import java.util.Locale

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun MainScreen(
    navController: NavController
) {
        // BottomNavigationBar 아래에 위치
        //BottomNavigationBar(navController = navController)
}

@Composable
fun BottomNavigationBar(navController: NavController,
) {

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    // 바텀 네비게이션 아이템
    val items = listOf(
        Screen.Equipment,
        Screen.BuffEnhance,
        Screen.Avatar,
        Screen.Creature
    )

    BottomNavigation(
        backgroundColor = Color.White, // 바텀 네비게이션 배경 색상
        contentColor = Color.Black // 아이콘 및 텍스트 색상
    ) {
        items.forEach { screen ->
            BottomNavigationItem(
                label = { Text(screen.title.capitalize(Locale.ROOT)) },
                icon = {
                    Icon(
                        painterResource(id = screen.icon), // 아이콘 리소스
                        contentDescription = screen.title,
                        modifier = Modifier
                            .size(30.dp) // 아이콘 크기 설정
                            .padding(5.dp)
                    )
                },
                selected = currentRoute == screen.route,
                selectedContentColor = Color.Blue, // 선택된 항목 색상
                unselectedContentColor = Color.Gray, // 선택되지 않은 항목 색상
                onClick = {
                    navController.navigate(screen.route) {
                        // 중복된 항목 클릭 시 이전 상태로 돌아가지 않도록 설정
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
sealed class Screen(val route: String, val title: String, val icon: Int) {
    object Equipment : Screen("장착장비", "장착장비", R.drawable.ic_equipment)
    object BuffEnhance : Screen("버프강화", "버프강화", R.drawable.ic_buff_enhance)
    object Avatar : Screen("아바타", "아바타", R.drawable.ic_avatar)
    object Creature : Screen("탈리스만", "탈리스만", R.drawable.ic_creature)
}