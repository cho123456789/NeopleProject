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
import com.example.myapplication.viewmodel.BufferEquipmentViewModel
import com.example.myapplication.viewmodel.CharacterEquipmentViewModel

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun BuffEquipmentScreen(
    navController: NavController,
    //equipmentList: List<BufferEquipment>
    viewModel: BufferEquipmentViewModel = hiltViewModel(),

    ) {
    val equipment by viewModel.bufferEquipment.collectAsState(initial = emptyList())
    val avatar by viewModel.bufferAvatar.collectAsState(initial = emptyList())
    val creature by viewModel.bufferCreature.collectAsState(initial = emptyList())
    val creatureName by viewModel.bufferCreatureName.collectAsState(initial = emptyList())
    val buffLevel by viewModel.bufflevel.collectAsState()
    val buffName by viewModel.buffname.collectAsState()
    val context = LocalContext.current
    val (characterId, serverId) = getCharacterId(context)


    LaunchedEffect(key1 = navController.currentBackStackEntry) {
        if (characterId != null && serverId != null) {
            Log.d("Character ID", "Retrieved Character ID: $characterId")
            Log.d("Server ID", "Retrieved Server ID: $serverId")
            viewModel.getBuffCharacterEquipment(serverId, characterId)
            viewModel.getBuffCharacterAvatar(serverId, characterId)
            viewModel.getBuffCharacterCreature(serverId, characterId)
        }
    }
    if (equipment.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(), // 화면 전체를 차지
            contentAlignment = Alignment.Center // 가운데 정렬
        ) {
            CircularProgressIndicator() // 가운데에 배치
        }
    } else {
        Log.d("BuffEquipmentScreen", equipment.toString())
        val creatureName = creature.map { it.itemName }
        val bufferPants = avatar.filter { it.slotId == "PANTS" }
        val bufferJacket = avatar.filter { it.slotId == "JACKET" }
        val weaponItems = equipment.filter { it.slotName == "무기" }
        val styleItems = equipment.filter { it.slotName == "칭호" }
        val subItems = equipment.filter { it.slotName == "보조무기" }
        val shoulderItems = equipment.filter { it.slotName == "머리어깨" }
        val upperBodyItems = equipment.filter { it.slotName == "상의" }
        val lowerBodyItems = equipment.filter { it.slotName == "하의" }
        val beltItems = equipment.filter { it.slotName == "벨트" }
        val shoesBodyItems = equipment.filter { it.slotName == "신발" }
        val braceletItems = equipment.filter { it.slotName == "팔찌" }
        val necklaceItems = equipment.filter { it.slotName == "목걸이" }
        val ringItems = equipment.filter { it.slotName == "반지" }
        val AuxItems = equipment.filter { it.slotName == "보조장비" }
        val ManaItems = equipment.filter { it.slotName == "마법석" }
        val earringItems = equipment.filter { it.slotName == "귀걸이" }


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
                                start = 20.dp,
                                top = 50.dp,
                                end = 5.dp,
                                bottom = 5.dp
                            )
                    ) {
                        IconButton(
                            modifier = Modifier.size(15.dp),
                            onClick = {
                                navController.navigate("home")
                            }) {
                            Icon(
                                painter = painterResource(id = R.drawable.arrow),
                                contentDescription = "뒤로가기"
                            )
                        }
                        Text(
                            modifier = Modifier
                                .padding(
                                    start = 10.dp,
                                    end = 5.dp,
                                    bottom = 5.dp,
                                    top = 20.dp
                                ),
                            text = buffName,
                            style = MaterialTheme.typography.h6
                        )

                        Text(
                            modifier = Modifier
                                .padding(
                                    start = 10.dp,
                                    end = 5.dp,
                                    bottom = 10.dp,
                                    top = 20.dp
                                ),
                            text = "+" + buffLevel.toString() + "렙",
                            style = MaterialTheme.typography.h6
                        )
                    }
                }
                items(weaponItems) { item ->
                    Text(
                        modifier = Modifier
                            .padding(
                                start = 25.dp,
                                end = 5.dp,
                                bottom = 5.dp,
                                top = 20.dp
                            ),
                        text = "무기",
                        style = MaterialTheme.typography.h6
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                    ) {
                        BufferItemCard(item)
                    }
                    Divider(
                        modifier = Modifier.padding(vertical = 5.dp),
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f), // 색상 조절
                        thickness = 1.dp // 두께 조절
                    )
                }
                item {
                    Text(
                        text = "칭호",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                items(styleItems) { item ->
                    BufferItemCard(item)
                    Divider(
                        modifier = Modifier.padding(vertical = 5.dp),
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f), // 색상 조절
                        thickness = 1.dp // 두께 조절
                    )
                }
                item {
                    Text(
                        text = "보조무기",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                items(subItems) { item ->
                    BufferItemCard(item)
                    Divider(
                        modifier = Modifier.padding(vertical = 5.dp),
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f), // 색상 조절
                        thickness = 1.dp // 두께 조절
                    )
                }
                item {
                    Text(
                        text = "머리어깨",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                items(shoulderItems) { item ->
                    BufferItemCard(item)
                    Divider(
                        modifier = Modifier.padding(vertical = 5.dp),
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f), // 색상 조절
                        thickness = 1.dp // 두께 조절
                    )
                }

                item {
                    Text(
                        text = "상의",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                items(upperBodyItems) { item ->
                    BufferItemCard(item)
                    Divider(
                        modifier = Modifier.padding(vertical = 5.dp),
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f), // 색상 조절
                        thickness = 1.dp // 두께 조절
                    )
                }
                item {
                    Text(
                        text = "하의",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                items(lowerBodyItems) { item ->
                    BufferItemCard(item)
                    Divider(
                        modifier = Modifier.padding(vertical = 5.dp),
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f), // 색상 조절
                        thickness = 1.dp // 두께 조절
                    )
                }

                item {
                    Text(
                        text = "벨트",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                items(beltItems) { item ->
                    BufferItemCard(item)
                    Divider(
                        modifier = Modifier.padding(vertical = 5.dp),
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f), // 색상 조절
                        thickness = 1.dp // 두께 조절
                    )
                }
                item {
                    Text(
                        text = "신발",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                items(shoesBodyItems) { item ->
                    BufferItemCard(item)
                    Divider(
                        modifier = Modifier.padding(vertical = 5.dp),
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f), // 색상 조절
                        thickness = 1.dp // 두께 조절
                    )
                }
                item {
                    Text(
                        text = "팔찌",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                items(braceletItems) { item ->
                    BufferItemCard(item)
                    Divider(
                        modifier = Modifier.padding(vertical = 5.dp),
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f), // 색상 조절
                        thickness = 1.dp // 두께 조절
                    )
                }
                item {
                    Text(
                        text = "목걸이",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                items(necklaceItems) { item ->
                    BufferItemCard(item)
                    Divider(
                        modifier = Modifier.padding(vertical = 5.dp),
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f), // 색상 조절
                        thickness = 1.dp // 두께 조절
                    )
                }
                item {
                    Text(
                        text = "반지",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                items(ringItems) { item ->
                    BufferItemCard(item)
                    Divider(
                        modifier = Modifier.padding(vertical = 5.dp),
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f), // 색상 조절
                        thickness = 1.dp // 두께 조절
                    )
                }
                item {
                    Text(
                        text = "보조장비",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                items(AuxItems) { item ->
                    BufferItemCard(item)
                    Divider(
                        modifier = Modifier.padding(vertical = 5.dp),
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f), // 색상 조절
                        thickness = 1.dp // 두께 조절
                    )
                }
                item {
                    Text(
                        text = "마법석",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                items(ManaItems) { item ->
                    BufferItemCard(item)
                    Divider(
                        modifier = Modifier.padding(vertical = 5.dp),
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f), // 색상 조절
                        thickness = 1.dp // 두께 조절
                    )
                }
                item {
                    Text(
                        text = "귀걸이",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                items(earringItems) { item ->
                    BufferItemCard(item)
                    Divider(
                        modifier = Modifier.padding(vertical = 5.dp),
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f), // 색상 조절
                        thickness = 1.dp // 두께 조절
                    )
                }
                item {
                    Text(
                        text = "레어 아바타 하의",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                items(bufferJacket) { item ->
                    BufferAvatarCard(item)
                    Divider(
                        modifier = Modifier.padding(vertical = 5.dp),
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f), // 색상 조절
                        thickness = 1.dp // 두께 조절
                    )
                }
                item {
                    Text(
                        text = "레어 아바타 상의",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                items(bufferPants) { item ->
                    BufferAvatarCard(item)
                    Divider(
                        modifier = Modifier.padding(vertical = 5.dp),
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f), // 색상 조절
                        thickness = 1.dp // 두께 조절
                    )
                }
                item {
                    Text(
                        text = "버프 강화 크리쳐",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                items(creatureName) { item ->
                    BufferCreatureCard(item)
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
fun BufferCreatureCard(creature: String) {
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
                    Text(
                        text = creature,
                        fontWeight = FontWeight.Bold,
                        color = Pink80,
                    )
                }
            }
        }
    }
}

@Composable
fun BufferAvatarCard(avatar: Avatar) {
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
                    if (avatar.slotId == "PANTS") {
                        Text(
                            text = avatar.emblems.firstOrNull()?.itemName ?: "No Emblem",
                            fontWeight = FontWeight.Bold,
                            color = Blue,
                        )
                    } else {
                        Column(
                            Modifier.wrapContentSize()
                        )
                        {
                            Text(
                                text = avatar.emblems.firstOrNull()?.itemName ?: "No Emblem",
                                fontWeight = FontWeight.Bold,
                                color = Blue,
                            )
                            Text(
                                text = "아바타 옵션 :" + avatar.optionAbility,
                                fontWeight = FontWeight.Bold,
                                color = Blue,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BufferItemCard(bufferEquipment: BufferEquipment) {
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
                    if (bufferEquipment.slotName == "칭호") {
                        Text(
                            text = BuffremovePlusSign(bufferEquipment.itemName),
                            fontWeight = FontWeight.Bold,
                            color = Blue,
                        )
                    } else {
                        Text(
                            text = BuffremovePlusSign(bufferEquipment.itemName),
                            fontWeight = FontWeight.Bold,
                            color = NameOragne,
                        )
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.End // 오른쪽 정렬
                        ) {
                            if (bufferEquipment.slotName == "칭호" || bufferEquipment.slotName == "보조무기") {
                                Text(
                                    text = "",
                                )
                            } else {
                                if (bufferEquipment.amplificationName == null) {
                                    Text(
                                        text = "+${bufferEquipment.reinforce}강화/(${bufferEquipment.refine})",
                                    )
                                } else {
                                    Text(
                                        text = "+${bufferEquipment.reinforce}증폭",
                                        color = PurpleBink,
                                        fontWeight = FontWeight.Bold
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

fun BuffremovePlusSign(text: String): String {
    return text.replace("+", " ")
}


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Preview(showBackground = true)
@Composable
internal fun BuffPreviewMainContainer() {
    val navController = rememberNavController()
    // 더미 데이터 생성
    val sampleItems = listOf(
        Item(
            itemName = "+Aegis+ Shield+",
            slotName = "Shield",
            itemType = "Defense",
            itemAvailableLevel = 8,
            itemRarity = "Rare",
            itemGradeName = "B",
            reinforce = 3,
            refine = 2,
            enchant = Enchant(
                explain = "12",
                status = listOf(
                    Status(
                        name = "123",
                        value = 12
                    )
                )
            ),
            amplificationName = "",
            itemId = "",
            itemTypeDetail = "",
            itemTypeDetailId = "",
            itemTypeId = "",
            setItem = SetItem(
                setItemId = "Dragon Set",
                setItemName = "Fire Damage +10%"
            ),
            sirocoInfo = SirocoInfo(
                options = listOf(
                    SirocoOption(
                        explain = "Attack Speed Increase",
                        explainDetail = "Increases attack speed by 20%",
                        buffExplain = "Attack Speed",
                        buffExplainDetail = "Increases attack speed by 20%."
                    ),
                    SirocoOption(
                        explain = "Critical Hit Chance",
                        explainDetail = "Increases critical hit chance by 15%",
                        buffExplain = "Critical Hit Chance",
                        buffExplainDetail = "Increases critical hit chance by 15%."
                    )
                )
            ),
            slotId = "",
            upgradeInfo = UpgradeInfo(
                itemId = "",
                itemName = ""
            ),
            transformInfo = TransformInfo(
                explain = "",
                explainDetail = "",
                optionType = "",
                active = true
            )
        ),
        Item(
            itemName = "Aegis Shield",
            slotName = "Shield",
            itemType = "Defense",
            itemAvailableLevel = 8,
            itemRarity = "Rare",
            itemGradeName = "B",
            reinforce = 3,
            refine = 2,
            enchant = null,
            amplificationName = "",
            itemId = "",
            itemTypeDetail = "",
            itemTypeDetailId = "",
            itemTypeId = "",
            setItem = SetItem(
                setItemId = "Dragon Set",
                setItemName = "Fire Damage +10%"
            ),
            sirocoInfo = SirocoInfo(
                options = listOf(
                    SirocoOption(
                        explain = "Attack Speed Increase",
                        explainDetail = "Increases attack speed by 20%",
                        buffExplain = "Attack Speed",
                        buffExplainDetail = "Increases attack speed by 20%."
                    ),
                    SirocoOption(
                        explain = "Critical Hit Chance",
                        explainDetail = "Increases critical hit chance by 15%",
                        buffExplain = "Critical Hit Chance",
                        buffExplainDetail = "Increases critical hit chance by 15%."
                    )
                )
            ),
            slotId = "",
            upgradeInfo = UpgradeInfo(
                itemId = "",
                itemName = ""
            ),
            transformInfo = TransformInfo(
                explain = "",
                explainDetail = "",
                optionType = "",
                active = true
            ),

            )
    )
    // Preview with the sample data
    MaterialTheme {
        EquipmentScreen(navController = navController)
    }
}