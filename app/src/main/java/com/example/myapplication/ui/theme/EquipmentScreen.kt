package com.example.myapplication.ui.theme

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.data.remote.dto.Item

@Composable
fun EquipmentScreen(
    equipmentList: List<Item>
) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf("장착장비", "버프강화", "크리쳐")

    // 각 탭에 해당하는 장비 목록 필터링 (탭에 따라 다르게 보여줄 데이터)
    val filteredList = when (selectedTabIndex) {
        0 -> equipmentList // All 탭일 경우 전체 리스트
//        1 -> equipmentList.filter { it.contains("Weapon") } // Weapons 탭일 경우 필터링
//        2 -> equipmentList.filter { it.name.contains("Armor") } // Armor 탭일 경우 필터링
        else -> emptyList()
    }
    Scaffold(
        topBar = {
            Column {
                // 상단 탭 네비게이션
                TabRow(
                    selectedTabIndex = selectedTabIndex,
                    modifier = Modifier.padding(8.dp)
                ) {
                    tabTitles.forEachIndexed { index, title ->
                        Tab(
                            text = { Text(title) },
                            selected = selectedTabIndex == index,
                            onClick = { selectedTabIndex = index }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        // LazyColumn을 사용하여 필터링된 장비 목록 표시
        Log.d("EquipmentScreen", filteredList.toString())
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(filteredList) { item ->
                ItemCard(item)
            }
        }
    }
}


@Composable
fun ItemCard(item: Item) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )
            {
                Text(
                    text = "무기",
                    color = Color.Black,  // Gold color
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
                ItemWeapon(item = item)
            }
            HorizontalDivider(thickness = 2.dp, color = Color.LightGray)

            // Weapon Section

            Spacer(modifier = Modifier.height(8.dp))

            // Sub-Weapon Section
            Text(
                text = "보조무기",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
            //ItemDetails(item = item)

            Spacer(modifier = Modifier.height(8.dp))

            // Head/Shoulder Section
            Text(
                text = "머리어깨",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
            //ItemDetails(item = item)

            Spacer(modifier = Modifier.height(8.dp))

            // Top Section
            Text(
                text = "상의",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
            //ItemDetails(item = item)
        }
    }
}

@Composable
fun ItemWeapon(item: Item) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top=5.dp,
                start=10.dp,
                end=10.dp,
                bottom = 5.dp
            ),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = item.itemName ?: "Unknown",
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFFB139),
            fontSize = 15.sp
        )
    }
}

@Composable
fun ItemDetails(item: Item) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = "Item Name:", fontWeight = FontWeight.Bold)
            Text(text = "Slot:")
            Text(text = "Type:")
            Text(text = "Available Level:")
            Text(text = "Rarity:")
            Text(text = "Grade:")
            Text(text = "Reinforce:")
            Text(text = "Refine:")
            item.enchant?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Enchant:", color = Color.Gray)  // Enchant text in gray
            }
        }

        Column(modifier = Modifier.weight(1f)) {
            Text(text = item.itemName ?: "Unknown", fontWeight = FontWeight.Bold)
            Text(text = item.slotName ?: "Unknown")
            Text(text = item.itemType ?: "Unknown")
            Text(text = item.itemAvailableLevel?.toString() ?: "Unknown")
            Text(text = item.itemRarity ?: "Unknown")
            Text(text = item.itemGradeName ?: "Unknown")
            Text(text = item.reinforce?.toString() ?: "Unknown")
            Text(text = item.refine?.toString() ?: "Unknown")

            item.enchant?.let { enchant ->
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = enchant.explain ?: "None", color = Color.Gray)  // Enchant text in gray
                enchant.status.forEach { status ->
                    Text(text = "  ${status.name}: ${status.value}", color = Color.Gray)
                }
            }
        }
    }
}

