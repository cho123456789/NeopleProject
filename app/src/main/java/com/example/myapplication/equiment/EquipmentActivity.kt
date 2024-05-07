package com.example.myapplication.equiment
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W300
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ServerViewModel
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
                .padding(5.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 2.dp, // 너비 5dp
                        color = Color(android.graphics.Color.parseColor("#E2E2E2")), // 색상 파란색
                        shape = RectangleShape // 네모 모양
                    )
            ) {
                Row(
                    modifier = Modifier
                        .padding(2.dp)
                        .wrapContentSize(),
                ) {
                    if(item.slotId == "WEAPON") {
                        val imageResource = when (item.itemName) {
                            "仙 : 결전의 도 - 데몬슬레이어" -> R.drawable.swordgril_end_do
                            "근원을 삼킨 도" -> R.drawable.swordgril_one_do
                            "얼어붙은 저항의 도" -> R.drawable.swordgirl_call_do
                            "내딛은 자의 도" -> R.drawable.swordgirl_none_do
                            "광폭화된 전의의 도" -> R.drawable.swordgirl_gawn_do
                            "사멸하는 신뢰의 도" -> R.drawable.swordgirl_posion_do
                            "火 : 불타는 고난의 도" -> R.drawable.swordgirl_fire_do
                            "水 : 오염된 눈의 도" -> R.drawable.swordgirl_water_do
                            "木 : 그늘진 새벽의 도" -> R.drawable.swordgirl_tree_do
                            "金 : 각인된 상처의 도" -> R.drawable.swordgirl_gold_do
                            "土 : 따뜻한 봄날의 도" -> R.drawable.swordgirl_mok_do
                            "부조화 : 무너진 경계의 도" -> R.drawable.swordgirl_drak_do
                            "불가침의 영역 - 도" -> R.drawable.swordgirl_bull_do
                            else -> R.drawable.defalut // 기본 이미지 설정
                        }
                        Text(
                            text = "${item.slotName}",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        Image(
                            painter = painterResource(id = imageResource),
                            contentDescription = "",
                            modifier = Modifier.padding(
                                start = 20.dp,
                                top = 5.dp,
                                end = 5.dp,
                                bottom = 5.dp
                            )
                        )
                    }
                        Text(
                            text = "${item.itemName}",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Right
                        )
                        Log.d("TAG",item.itemName)


//                    Text(
//                        text = "${item.upgradeInfo?.itemName}",
//                        color = Color.Red,
//                        fontWeight = FontWeight.Bold,
//                        fontSize = 13.sp,
//                        textAlign = TextAlign.Right
//                    )
//                    val enchantName = item.enchant?.status?.map { it.name }?.toMutableList()
//                    var enchantExplain = item.enchant?.explain
//                    if(enchantExplain == null){
//                        enchantExplain = ""
//                    }
//
//                    val enchantValue = item.enchant?.status?.map { it.value }
//
//                    val combinedString = StringBuilder()
//
//                    if (enchantName != null) {
//                        for (i in enchantName.indices) {
//                            combinedString.append("${enchantName[i]} + ${enchantValue?.get(i)} "+"\n")
//                        }
//                    }
//
//                    Column(){
//                        if (enchantName != null && enchantExplain.isEmpty()) {
//                            Text(
//                                text = combinedString.toString(),
//                                color = Color(0xFFAD96FF),
//                                fontWeight = FontWeight.Bold,
//                                fontSize = 13.sp,
//                                textAlign = TextAlign.Right
//                            )
//                        }else{
//                            Text(
//                                text = enchantExplain +"\n" +combinedString.toString(),
//                                color = Color(0xFFAD96FF),
//                                fontWeight = FontWeight.Bold,
//                                fontSize = 13.sp,
//                                textAlign = TextAlign.Right
//                            )
//                        }
//
//                    }
//                }
//            }
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