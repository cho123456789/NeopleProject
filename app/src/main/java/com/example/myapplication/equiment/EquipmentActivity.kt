package com.example.myapplication.equiment
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W300
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import com.database.characterDatabase
import com.dto.CharacterDto
import com.example.dao.CharacterDao
import com.example.myapplication.ServerViewModel
import com.example.myapplication.network.CharacterEquipment
import com.example.myapplication.network.Item


class EquipmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    OtherActivityContent(serverViewModel = ServerViewModel())
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun OtherActivityContent(serverViewModel: ServerViewModel) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = 5.dp,
                    start = 30.dp,
                    end = 20.dp
                ),
        ) {

            val characterItem by serverViewModel.characterId.collectAsState()
            val characterName by serverViewModel.characterName.collectAsState()

            Log.d(
                "TAG", "characterItem : ${characterItem} " +
                        "" + "characterName : ${characterName} "
            )

            val characterItemId = characterItem.joinToString(", ")
            serverViewModel.getCharacterId("cain", characterName)
            serverViewModel.getCharacterEquiment("cain", characterItemId)

            Box(
                modifier = Modifier.background(
                    color = Color.Gray
                )
            ) {
                Text(
                    text = "장착 장비 : Equipment",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = W300,
                    modifier = Modifier.padding(
                        5.dp
                    )
                )
            }

                val slotName by serverViewModel.slotName.collectAsState()
                val itemType by serverViewModel.itemType.collectAsState()
                val equipment by serverViewModel.equipment.collectAsState()
                Log.d("TAG","characterEqResponse : ${equipment}")
                EquipmentList(equipment)
                Text(
                    text = slotName,
                    color = Color.Black
                )
                Text(text = itemType)
            }
    }
    @Composable
    fun EquipmentList(equipment: List<Item>){
        LazyColumn {
            itemsIndexed(equipment) { index, item ->
                if (index != 1) {
                    EquipmentRow(item)
                }
            }
        }
    }
    @Composable
    fun EquipmentRow(item: Item) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .padding(2.dp)
                    .wrapContentSize(),
            ) {
                Text(
                    text = "${item.slotName}",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(
                modifier = Modifier
                    .padding(2.dp)
                    .wrapContentSize(),
                    verticalArrangement = Arrangement.Center,
                    Alignment.End
            ) {
                Text(
                    text = "${item.itemName}",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Right
                )
                Text(
                    text = "${item.upgradeInfo?.itemName}",
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    textAlign = TextAlign.Right
                )
                val enchantName = item.enchant?.status?.map { it.name }?.toMutableList()
                var enchantExplain = item.enchant?.explain
                if(enchantExplain == null){
                    enchantExplain = ""
                }

                val enchantValue = item.enchant?.status?.map { it.value }

                val combinedString = StringBuilder()

                if (enchantName != null) {
                    for (i in enchantName.indices) {
                        combinedString.append("${enchantName[i]} + ${enchantValue?.get(i)} "+"\n")
                    }
                }

                Column(){
                    if (enchantName != null && enchantExplain.isEmpty()) {
                        Text(
                            text = combinedString.toString(),
                            color = Color(0xFFAD96FF),
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            textAlign = TextAlign.Right
                        )
                    }else{
                            Text(
                                text = enchantExplain +"\n" +combinedString.toString(),
                                color = Color(0xFFAD96FF),
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                textAlign = TextAlign.Right
                            )
                    }

                }
            }
        }
    }

}

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        MaterialTheme {
            Surface {
                //OtherActivityContent()
            }
    }
}