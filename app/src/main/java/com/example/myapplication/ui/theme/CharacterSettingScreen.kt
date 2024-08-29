package com.example.myapplication.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R

@Composable
fun CharacterSettingScreen(
    adventureName: String,
    characterName : String,
    characterSever: String,
    jobGrowName : String,
    GuildName : String,
    ImageProfile : ImageBitmap
) {
    Box(
        modifier = Modifier
            .padding(5.dp)
            .width(200.dp) // 너비 조절
            .height(350.dp) // 높이 조절
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Gear grid around the character
            //GearGrid()

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

@Composable
fun GearGrid() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
            .background(Color.White)
    ) {
        // Define your grid layout here, example using a 3x3 grid
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                // Top left gear
                GearIcon(R.drawable.gear_placeholder)
                Spacer(modifier = Modifier.height(8.dp))
            }

            Column {
                // Top right gear
                GearIcon(R.drawable.gear_placeholder)
                Spacer(modifier = Modifier.height(8.dp))
                // Right middle gear
                GearIconWithBorder(R.drawable.gear_placeholder)
            }
        }
    }
}

@Composable
fun GearIcon(resourceId: Int) {
    Image(
        painter = painterResource(id = resourceId),
        contentDescription = null,
        modifier = Modifier.size(480.dp)
    )
    Spacer(modifier = Modifier.height(16.dp)) // 이미지 아래 공간 추가
}

@Composable
fun CharacterImage(
    ImageProfile: ImageBitmap
) {
    Image(
        painter = BitmapPainter(ImageProfile),
        contentDescription = "Character Image",
        modifier = Modifier
            .size(200.dp)
            .padding(top = 0.dp) // 상단 padding을 0으로 설정하여 상단에 배치
            .fillMaxWidth() // 전체 너비에 맞게 이미지 크기 조정
    )
}

@Composable
fun CharacterStats(
    adventureName : String,
    characterName : String,
    characterSever: String,
    jobGrowName : String,
    GuildName : String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = adventureName,
            color = Color.Blue,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = jobGrowName,
            color = Color.Black,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = characterName + " | " +  characterSever,
            color = Color.Black,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "[" + GuildName + "]",
            color = Color.Black,
            style = MaterialTheme.typography.bodyMedium
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