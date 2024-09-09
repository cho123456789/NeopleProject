package com.example.myapplication.ui.theme

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.data.remote.dto.Item

@Composable
fun EquipmentScreen(
    equipmentList: List<Item>
) {
    Log.d("EquipmentScreen", equipmentList.toString())
    LazyColumn {
        items(equipmentList) { item ->
            ItemCard(item)
        }
    }
}

@Composable
fun ItemCard(item: Item) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Item Name: ${item.itemName}", fontWeight = FontWeight.Bold)
            Text(text = "Slot: ${item.slotName}")
            Text(text = "Type: ${item.itemType}")
            Text(text = "Available Level: ${item.itemAvailableLevel}")
            Text(text = "Rarity: ${item.itemRarity}")
            Text(text = "Grade: ${item.itemGradeName}")
            Text(text = "Reinforce: ${item.reinforce}")
            Text(text = "Refine: ${item.refine}")

            item.enchant?.let { enchant ->
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Enchant: ${enchant.explain ?: "None"}")
                enchant.status.forEach { status ->
                    Text(text = "  ${status.name}: ${status.value}")
                }
            }

            item.upgradeInfo?.let { upgradeInfo ->
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Upgrade Info: ${upgradeInfo.itemName}")
            }
        }
    }
}