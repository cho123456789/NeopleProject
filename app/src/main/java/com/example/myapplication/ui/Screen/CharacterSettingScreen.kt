import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun CharacterSettingScreen(
    adventureName: String,
    characterName: String,
    characterSever: String,
    jobGrowName: String,
    GuildName: String,
    ImageProfile: ImageBitmap
) {
    val navController = rememberNavController()

    // NavHost 설정, 화면 간 경로(route)를 정의합니다.
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            // Home 화면은 현재 CharacterSettingScreen의 내용입니다.
            HomeScreen(
                navController,
                adventureName,
                characterName,
                characterSever,
                jobGrowName,
                GuildName,
                ImageProfile
            )
        }
    }
}

@Composable
fun HomeScreen(
    navController: NavHostController,
    adventureName: String,
    characterName: String,
    characterSever: String,
    jobGrowName: String,
    GuildName: String,
    ImageProfile: ImageBitmap
) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .wrapContentSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
//            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            // Character Image
            CharacterImage(ImageProfile = ImageProfile)

            Spacer(modifier = Modifier.height(10.dp))

            // Character Stats
            CharacterStats(
                adventureName,
                characterName,
                characterSever,
                jobGrowName,
                GuildName
            )
        }
    }
}


// 다른 함수들 - CharacterImage, CharacterStats
@Composable
fun CharacterImage(ImageProfile: ImageBitmap) {
    Image(bitmap = ImageProfile, contentDescription = null)
}

@Composable
fun CharacterStats(
    adventureName: String,
    characterName: String,
    characterSever: String,
    jobGrowName: String,
    GuildName: String
) {
    Column(
        modifier = Modifier
            .padding(0.dp)
    ) {
        Text(
            text = adventureName,
            color = Color.Blue,
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = jobGrowName,
            color = Color.Black,
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = characterName + " | " + characterSever,
            color = Color.Black,
            style = MaterialTheme.typography.h6
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "[" + GuildName + "]",
            color = Color.Black,
            style = MaterialTheme.typography.h6
        )
    }
}


@Composable
fun GearIconWithBorder(resourceId: Int) {
    Box(
        modifier = Modifier
            .size(48.dp)  // 네모의 크기 설정
            .border(2.dp, Color.Yellow)  // 테두리 두께와 색상 설정
            .background(Color.DarkGray)  // 배경색 설정
            .padding(4.dp)  // 이미지와 테두리 사이에 패딩을 추가
    ) {
        Image(
            painter = painterResource(id = resourceId),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}

fun getServerNameById(serverId: String): String? {
    val serverMap = mapOf(
        "cain" to "카인",
        "diregie" to "디레지에",
        "siroco" to "시로코",
        "prey" to "프레이",
        "casillas" to "카시야스",
        "hilder" to "힐더",
        "anton" to "안톤",
        "bakal" to "바칼"
    )

    return serverMap[serverId]
}
