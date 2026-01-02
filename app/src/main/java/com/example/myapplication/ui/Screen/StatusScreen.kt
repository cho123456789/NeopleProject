package com.example.myapplication.ui.Screen

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.data.remote.dto.BuffInfo
import com.data.remote.dto.StatusInfo
import com.example.myapplication.viewmodel.StatusViewModel

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun StatusScreen(
    navController: NavController,
    serverId: String,
    characterId: String,
    viewModel: StatusViewModel = hiltViewModel()
) {
    val statusInfo by viewModel.statusInfo.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    LaunchedEffect(serverId, characterId) {
        viewModel.getStatus(serverId, characterId)
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when {
            isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            errorMessage != null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = errorMessage ?: "오류가 발생했습니다",
                        color = Color.Red,
                        fontSize = 16.sp
                    )
                }
            }

            statusInfo != null -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // 캐릭터 기본 정보
                    item {
                        CharacterBasicInfo(
                            characterName = statusInfo!!.characterName,
                            jobGrowName = statusInfo!!.jobGrowName,
                            level = statusInfo!!.level,
                            adventureName = statusInfo!!.adventureName,
                            guildName = statusInfo!!.guildName ?: "길드 없음",
                            fame = statusInfo!!.fame
                        )
                    }

                    // 버프 정보
                    statusInfo!!.buff?.let { buffs ->
                        item {
                            Text(
                                text = "버프 정보",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                        items(buffs) { buff ->
                            BuffCard(buff)
                        }
                    }

                    // 능력치 정보
                    item {
                        Text(
                            text = "능력치 정보",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }

                    items(statusInfo!!.status) { stat ->
                        StatusItem(stat)
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterBasicInfo(
    characterName: String,
    jobGrowName: String,
    level: Int,
    adventureName: String,
    guildName: String,
    fame: Int
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = characterName,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "$jobGrowName (Lv.$level)", fontSize = 16.sp)
            Text(text = "모험단: $adventureName", fontSize = 14.sp, color = Color.Gray)
            Text(text = "길드: $guildName", fontSize = 14.sp, color = Color.Gray)
            Text(text = "명성: $fame", fontSize = 14.sp, color = Color.Gray)
        }
    }
}

@Composable
fun BuffCard(buff: BuffInfo) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color(0xFFF5F5F5)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = buff.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                buff.level?.let {
                    Text(
                        text = "Lv.$it",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            buff.status.forEach { stat ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = stat.name, fontSize = 14.sp)
                    Text(
                        text = stat.value.toString(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1976D2)
                    )
                }
            }
        }
    }
}

@Composable
fun StatusItem(stat: StatusInfo) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 1.dp,
        shape = RoundedCornerShape(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stat.name,
                fontSize = 15.sp,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = stat.value.toString(),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2196F3)
            )
        }
    }
}
