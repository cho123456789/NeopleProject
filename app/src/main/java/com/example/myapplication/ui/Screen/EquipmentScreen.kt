package com.example.myapplication.ui.Screen

import android.graphics.drawable.Icon
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
import com.data.remote.dto.Enchant
import com.data.remote.dto.Item
import com.data.remote.dto.SetItem
import com.data.remote.dto.SirocoInfo
import com.data.remote.dto.SirocoOption
import com.data.remote.dto.Status
import com.data.remote.dto.TransformInfo
import com.data.remote.dto.UpgradeInfo
import com.example.myapplication.R
import com.example.myapplication.ui.theme.NameYellow
import com.example.myapplication.ui.theme.PurpleBink
import com.example.myapplication.viewmodel.CharacterEquipmentViewModel
import com.example.myapplication.viewmodel.CharacterInfoViewModel

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun EquipmentScreen(
    navController: NavController,
    //equipmentList: List<Item>,
    viewModel: CharacterEquipmentViewModel = hiltViewModel(),
) {

    val equipment by viewModel.equipment.collectAsState(initial = emptyList())

    val context = LocalContext.current
    val (characterId, serverId) = getCharacterId(context)

    LaunchedEffect(key1 = navController.currentBackStackEntry) {
        if (characterId != null && serverId != null) {
            Log.d("Character ID", "Retrieved Character ID: $characterId")
            Log.d("Server ID", "Retrieved Server ID: $serverId")
            viewModel.getCharacterEquipment(serverId, characterId)
        }
    }
    if (equipment.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(), // 화면 전체를 차지
            contentAlignment = Alignment.Center // 가운데 정렬
        ) {
            CircularProgressIndicator() // 가운데에 배치
        }
    }
    else {
        Log.d("EquipmentScreen", equipment.toString())
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

                        ItemCard(item)
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
                    ItemCard(item)
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
                    ItemCard(item)
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
                    ItemCard(item)
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
                    ItemCard(item)
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
                    ItemCard(item)
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
                    ItemCard(item)
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
                    ItemCard(item)
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
                    ItemCard(item)
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
                    ItemCard(item)
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
                    ItemCard(item)
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
                    ItemCard(item)
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
                    ItemCard(item)
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
                    ItemCard(item)
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
fun ItemCard(item: Item) {
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
                        text = removePlusSign(item.itemName),
                        fontWeight = FontWeight.Bold,
                        color = NameYellow,
                    )
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.End // 오른쪽 정렬
                    ) {
                        if (item.slotName == "칭호" || item.slotName == "보조무기") {
                            Text(
                                text = "",
                            )
                        } else {
                            if (item.amplificationName == null) {
                                Text(
                                    text = "+${item.reinforce}강화/(${item.refine})",
                                )
                            } else {
                                Text(
                                    text = "+${item.reinforce}증폭",
                                    color = PurpleBink,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp)
                )
                {
                    item.upgradeInfo?.let { upgradeInfo ->
                        Spacer(modifier = Modifier.height(1.dp))
                        Text(
                            text = "ㄴ ${removePlusSign(upgradeInfo.itemName)}",
                            color = NameYellow
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                    }

                    item.enchant?.let { enchant ->
                        enchant.explain?.let { explain ->
                            Text(text = removePlusSign(enchant.explain))
                        }
                        Text(
                            text = enchant.status.joinToString(", ") { removePlusSign("${(it.name)}: ${it.value}") },
                            fontSize = 15.sp
                        )
                    }
                }
            }
        }
    }
}

fun removePlusSign(text: String): String {
    return text.replace("+", " ")
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Preview(showBackground = true)
@Composable
internal fun PreviewMainContainer() {
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