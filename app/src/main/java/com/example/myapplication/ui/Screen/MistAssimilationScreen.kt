package com.example.myapplication.ui.Screen

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.data.remote.dto.MistStatus
import com.example.myapplication.viewmodel.MistAssimilationViewModel

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun MistAssimilationScreen(
    navController: NavController,
    serverId: String,
    characterId: String,
    viewModel: MistAssimilationViewModel = hiltViewModel()
) {
    val mistInfo by viewModel.mistAssimilationInfo.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    LaunchedEffect(serverId, characterId) {
        viewModel.getMistAssimilation(serverId, characterId)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        when {
            isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            errorMessage != null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = errorMessage ?: "오류가 발생했습니다",
                        color = Color.Red,
                        fontSize = 16.sp
                    )
                }
            }

            mistInfo != null -> {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // 캐릭터 기본 정보
                    item {
                        MistCharacterInfo(
                            characterName = mistInfo!!.characterName,
                            jobGrowName = mistInfo!!.jobGrowName,
                            level = mistInfo!!.level,
                            adventureName = mistInfo!!.adventureName,
                            guildName = mistInfo!!.guildName ?: "길드 없음"
                        )
                    }

                    // 안개융화 정보
                    mistInfo!!.mistAssimilation?.let { mist ->
                        item {
                            MistLevelCard(
                                level = mist.level,
                                expRate = mist.expRate
                            )
                        }

                        item {
                            Text(
                                text = "안개융화 효과",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }

                        items(mist.status) { stat ->
                            MistStatusItem(stat)
                        }
                    } ?: item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = 2.dp,
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = "안개융화 정보가 없습니다.",
                                modifier = Modifier.padding(16.dp),
                                fontSize = 16.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        }

        // 하단 네비게이션
        BottomNavigationBar(navController = navController)
    }
}

@Composable
fun MistCharacterInfo(
    characterName: String,
    jobGrowName: String,
    level: Int,
    adventureName: String,
    guildName: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = characterName,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1976D2)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "$jobGrowName (Lv.$level)", fontSize = 16.sp)
            Text(text = "모험단: $adventureName", fontSize = 14.sp, color = Color.Gray)
            Text(text = "길드: $guildName", fontSize = 14.sp, color = Color.Gray)
        }
    }
}

@Composable
fun MistLevelCard(level: Int, expRate: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 6.dp,
        shape = RoundedCornerShape(12.dp),
        backgroundColor = Color(0xFF673AB7)
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "안개융화 레벨",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = "Lv.",
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = level.toString(),
                        fontSize = 36.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "경험치",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White.copy(alpha = 0.2f))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = expRate,
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun MistStatusItem(stat: MistStatus) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF9C27B0))
                )
                Text(
                    text = stat.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Text(
                text = stat.value.toString(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF673AB7)
            )
        }
    }
}
