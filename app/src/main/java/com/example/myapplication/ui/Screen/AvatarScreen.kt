package com.example.myapplication.ui.Screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.data.remote.dto.Avatar
import com.data.remote.dto.AvatarItem
import com.data.remote.dto.BufferEquipment
import com.data.remote.dto.CreatureDto
import com.data.remote.dto.Enchant
import com.data.remote.dto.Item
import com.data.remote.dto.SetItem
import com.data.remote.dto.SirocoInfo
import com.data.remote.dto.SirocoOption
import com.data.remote.dto.Status
import com.data.remote.dto.TransformInfo
import com.data.remote.dto.UpgradeInfo
import com.example.myapplication.R
import com.example.myapplication.ui.theme.Blue
import com.example.myapplication.ui.theme.NameOragne
import com.example.myapplication.ui.theme.NameYellow
import com.example.myapplication.ui.theme.Pink80
import com.example.myapplication.ui.theme.Purple80
import com.example.myapplication.ui.theme.PurpleBink
import com.example.myapplication.ui.theme.PurpleGrey40
import com.example.myapplication.ui.theme.YellowNormal
import com.example.myapplication.ui.theme.allNomarl
import com.example.myapplication.viewmodel.AvatarViewModel
import com.example.myapplication.viewmodel.BufferEquipmentViewModel
import com.example.myapplication.viewmodel.CharacterEquipmentViewModel

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun AvatarScreen(
    navController: NavController,
    //equipmentList: List<BufferEquipment>
    viewModel: AvatarViewModel = hiltViewModel(),

    ) {
    val avatar by viewModel.AvatarItem.collectAsState(initial = emptyList())
    val context = LocalContext.current
    val (characterId, serverId) = getCharacterId(context)


    LaunchedEffect(key1 = navController.currentBackStackEntry) {
        if (characterId != null && serverId != null) {
            Log.d("Character ID", "Retrieved Character ID: $characterId")
            Log.d("Server ID", "Retrieved Server ID: $serverId")
            viewModel.getAvatar(serverId, characterId)

        }
    }
    if (avatar.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(), // 화면 전체를 차지
            contentAlignment = Alignment.Center // 가운데 정렬
        ) {
            CircularProgressIndicator() // 가운데에 배치
        }
    } else {
        Log.d("AvatarItemScreen", avatar.toString())

        val header = avatar.filter { it.slotId == "HEADGEAR" }
        val hair = avatar.filter { it.slotId == "HAIR" }
        val face = avatar.filter { it.slotId == "FACE" }
        val jacket = avatar.filter { it.slotId == "JACKET" }
        val pants = avatar.filter { it.slotId == "PANTS" }
        val shoes = avatar.filter { it.slotId == "SHOES" }
        val breast = avatar.filter { it.slotId == "BREAST" }
        val waist = avatar.filter { it.slotId == "WAIST" }
        val skin = avatar.filter { it.slotId == "SKIN" }
        val aurora = avatar.filter { it.slotId == "AURORA" }
        val weapon = avatar.filter { it.slotId == "WEAPON" }
        val aura_skin = avatar.filter { it.slotId == "AURA_SKIN" }

        Column(
            modifier = Modifier.fillMaxSize() // 전체 화면 차지
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 20.dp, top = 50.dp, end = 5.dp, bottom = 5.dp
                            )
                    ) {
                        IconButton(modifier = Modifier.size(15.dp), onClick = {
                            navController.navigate("캐릭터검색")
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.arrow),
                                contentDescription = "뒤로가기"
                            )
                        }
                    }
                }
                items(header) { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                    ) {
                        avatarCard(item)
                    }
                    Divider(
                        modifier = Modifier.padding(vertical = 5.dp),
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f), // 색상 조절
                        thickness = 1.dp // 두께 조절
                    )
                }
                items(hair) { item ->
                    avatarCard(item)
                    Divider(
                        modifier = Modifier.padding(vertical = 5.dp),
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f), // 색상 조절
                        thickness = 1.dp // 두께 조절
                    )
                }
                items(face) { item ->
                    avatarCard(item)
                    Divider(
                        modifier = Modifier.padding(vertical = 5.dp),
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f), // 색상 조절
                        thickness = 1.dp // 두께 조절
                    )
                }
                items(breast) { item ->
                    avatarCard(item)
                    Divider(
                        modifier = Modifier.padding(vertical = 5.dp),
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f), // 색상 조절
                        thickness = 1.dp // 두께 조절
                    )
                }
                items(jacket) { item ->
                    avatarCard(item)
                    Divider(
                        modifier = Modifier.padding(vertical = 5.dp),
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f), // 색상 조절
                        thickness = 1.dp // 두께 조절
                    )
                }
                items(pants) { item ->
                    avatarCard(item)
                    Divider(
                        modifier = Modifier.padding(vertical = 5.dp),
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f), // 색상 조절
                        thickness = 1.dp // 두께 조절
                    )
                }
                items(shoes) { item ->
                    avatarCard(item)
                    Divider(
                        modifier = Modifier.padding(vertical = 5.dp),
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f), // 색상 조절
                        thickness = 1.dp // 두께 조절
                    )
                }
                items(waist) { item ->
                    avatarCard(item)
                    Divider(
                        modifier = Modifier.padding(vertical = 5.dp),
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f), // 색상 조절
                        thickness = 1.dp // 두께 조절
                    )
                }
                items(skin) { item ->
                    avatarCard(item)
                    Divider(
                        modifier = Modifier.padding(vertical = 5.dp),
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f), // 색상 조절
                        thickness = 1.dp // 두께 조절
                    )
                }
                items(aurora) { item ->
                    avatarCard(item)
                    Divider(
                        modifier = Modifier.padding(vertical = 5.dp),
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f), // 색상 조절
                        thickness = 1.dp // 두께 조절
                    )
                }
                items(weapon) { item ->
                    avatarCard(item)
                    Divider(
                        modifier = Modifier.padding(vertical = 5.dp),
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f), // 색상 조절
                        thickness = 1.dp // 두께 조절
                    )
                }

            }
            BottomNavigationBar(navController = navController)
        }
    }
}

@Composable
fun avatarCard(avatarItem: AvatarItem) {
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween // 아이템을 양쪽 끝으로 정렬
        ) {
            Column(
                horizontalAlignment = Alignment.Start // 왼쪽 정렬
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp)
                ) {
                    Row(
                        Modifier.fillMaxSize()
                    )
                    {
                        Column(
                            Modifier.wrapContentSize()
                        ) {
                            if (avatarItem.itemRarity == "레어") {
                                Text(
                                    text = avatarItem.itemName,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Magenta,
                                )
                            } else if (avatarItem.itemRarity == "커먼") {
                                Text(
                                    text = avatarItem.itemName,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Gray,
                                    fontSize = 14.sp
                                )
                            }
                            if (avatarItem.optionAbility != null) {
                                avatarItem.optionAbility.let {
                                    Text(
                                        text = it,
                                        fontWeight = FontWeight.Bold,
                                        color = NameOragne,
                                        fontSize = 13.sp
                                    )
                                }
                                if (avatarItem.clone != null) {
                                    avatarItem.clone.itemName?.let {
                                        Text(
                                            text = it,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Black,
                                            fontSize = 10.sp
                                        )
                                    }
                                }
                            }
                        }
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            if (avatarItem.emblems != null) {
                                if (avatarItem.emblems.getOrNull(0)?.slotColor == "붉은빛") {
                                    Column(
                                        Modifier.wrapContentSize()
                                    ) {
                                        Text(
                                            text = avatarItem.emblems.getOrNull(0)?.itemName
                                                ?: "No Emblem",
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Red,
                                            fontSize = 12.sp
                                        )
                                        Text(
                                            text = avatarItem.emblems.getOrNull(1)?.itemName
                                                ?: "No Emblem",
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Red,
                                            fontSize = 12.sp
                                        )
                                    }
                                } else if (avatarItem.emblems.getOrNull(0)?.slotColor == "노란빛") {
                                    Column(
                                        Modifier.wrapContentSize()
                                    ) {
                                        Text(
                                            text = avatarItem.emblems.getOrNull(0)?.itemName
                                                ?: "No Emblem",
                                            fontWeight = FontWeight.Bold,
                                            color = YellowNormal,
                                            fontSize = 12.sp

                                        )
                                        Text(
                                            text = avatarItem.emblems.getOrNull(1)?.itemName
                                                ?: "No Emblem",
                                            fontWeight = FontWeight.Bold,
                                            color = YellowNormal,
                                            fontSize = 12.sp

                                        )
                                    }
                                } else if (avatarItem.emblems.getOrNull(0)?.slotColor == "플래티넘") {
                                    Column(
                                        Modifier.wrapContentSize()
                                    )
                                    {
                                        Text(
                                            text = avatarItem.emblems.getOrNull(0)?.itemName
                                                ?: "No Emblem",
                                            fontWeight = FontWeight.Bold,
                                            color = NameYellow,
                                            fontSize = 12.sp
                                        )
                                        Text(
                                            text = avatarItem.emblems.getOrNull(1)?.itemName
                                                ?: "No Emblem",
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Green,
                                            fontSize = 12.sp
                                        )
                                        Text(
                                            text = avatarItem.emblems.getOrNull(2)?.itemName
                                                ?: "No Emblem",
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Green,
                                            fontSize = 12.sp
                                        )
                                    }

                                } else if (avatarItem.emblems.getOrNull(0)?.slotColor == "푸른빛") {
                                    Column(
                                        Modifier.wrapContentSize()
                                    ) {
                                        Text(
                                            text = avatarItem.emblems.getOrNull(0)?.itemName
                                                ?: "No Emblem",
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Blue,
                                            fontSize = 12.sp

                                        )
                                        Text(
                                            text = avatarItem.emblems.getOrNull(1)?.itemName
                                                ?: "No Emblem",
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Blue,
                                            fontSize = 12.sp

                                        )
                                    }
                                } else if (avatarItem.emblems.getOrNull(0)?.slotColor == "다색") {
                                    Column(
                                        Modifier.wrapContentSize()
                                    ) {
                                        Text(
                                            text = avatarItem.emblems.getOrNull(0)?.itemName
                                                ?: "No Emblem",
                                            fontWeight = FontWeight.Bold,
                                            color = allNomarl,
                                            fontSize = 12.sp
                                        )
                                        Text(
                                            text = avatarItem.emblems.getOrNull(1)?.itemName
                                                ?: "No Emblem",
                                            fontWeight = FontWeight.Bold,
                                            color = allNomarl,
                                            fontSize = 12.sp
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}