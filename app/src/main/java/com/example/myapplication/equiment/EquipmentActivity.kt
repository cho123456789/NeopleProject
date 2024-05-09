package com.example.myapplication.equiment
import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.size
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
                    start = 20.dp,
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
    @SuppressLint("InvalidColorHexValue")
    @Composable
    fun EquipmentRow(item: Item) {
        Row(
            modifier = Modifier
                .padding(1.dp)
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
                    if (item.slotId == "WEAPON") {
                        val imageResource = when {
                            item.itemName.contains("결전의 도") -> R.drawable.swordgirl_end_do
                            item.itemName.contains("결전의 소검") -> R.drawable.swordgirl_end_so
                            item.itemName.contains("결전의 대검") -> R.drawable.swordgirl_end_big
                            item.itemName.contains("결전의 둔기") -> R.drawable.swordgirl_end_ham
                            item.itemName.contains("결전의 광검") -> R.drawable.swordgirl_end_bim

                            item.itemName.contains("넘어선 기억의 도") -> R.drawable.swordgirl_bull_new_do
                            item.itemName.contains("넘어선 기억의 소검") -> R.drawable.swordgirl_bull_new_so
                            item.itemName.contains("넘어선 기억의 대검") -> R.drawable.swordgirl_bull_new_big
                            item.itemName.contains("넘어선 기억의 둔기") -> R.drawable.swordgirl_bull_new_ham
                            item.itemName.contains("넘어선 기억의 광검") -> R.drawable.swordgirl_bull_new_bim

                            item.itemName.contains("근원을 삼킨 도") -> R.drawable.swordgirl_one_do
                            item.itemName.contains("얼어붙은 저항의 도") -> R.drawable.swordgirl_call_do
                            item.itemName.contains("내딛은 자의 도") -> R.drawable.swordgirl_none_do
                            item.itemName.contains("광폭화된 전의의 도") -> R.drawable.swordgirl_gawn_do
                            item.itemName.contains("사멸하는 신뢰의 도") -> R.drawable.swordgirl_posion_do
                            item.itemName.contains("火 : 불타는 고난의 도") -> R.drawable.swordgirl_fire_do
                            item.itemName.contains("水 : 오염된 눈의 도") -> R.drawable.swordgirl_water_do
                            item.itemName.contains("木 : 그늘진 새벽의 도") -> R.drawable.swordgirl_tree_do
                            item.itemName.contains("金 : 각인된 상처의 도") -> R.drawable.swordgirl_gold_do
                            item.itemName.contains("土 : 따뜻한 봄날의 도") -> R.drawable.swordgirl_mok_do
                            item.itemName.contains("부조화 : 무너진 경계의 도") -> R.drawable.swordgirl_drak_do
                            item.itemName.contains("불가침의 영역 - 도") -> R.drawable.swordgirl_bull_do

                            item.itemName.contains("근원을 삼킨 소검") -> R.drawable.swordgirl_one_so
                            item.itemName.contains("얼어붙은 저항의 소검") -> R.drawable.swordgirl_call_so
                            item.itemName.contains("내딛은 자의 소검") -> R.drawable.swordgirl_none_so
                            item.itemName.contains("광폭화된 전의의 소검") -> R.drawable.swordgirl_gawn_so
                            item.itemName.contains("사멸하는 신뢰의 소검") -> R.drawable.swordgirl_posion_so
                            item.itemName.contains("火 : 불타는 고난의 소검") -> R.drawable.swordgirl_fire_so
                            item.itemName.contains("水 : 오염된 눈의 소검") -> R.drawable.swordgirl_water_so
                            item.itemName.contains("木 : 그늘진 새벽의 소검") -> R.drawable.swordgirl_tree_so
                            item.itemName.contains("金 : 각인된 상처의 소검") -> R.drawable.swordgirl_gold_so
                            item.itemName.contains("土 : 따뜻한 봄날의 소검") -> R.drawable.swordgirl_mok_so
                            item.itemName.contains("부조화 : 무너진 경계의 소검") -> R.drawable.swordgirl_drak_so
                            item.itemName.contains("불가침의 영역 - 소검") -> R.drawable.swordgirl_bull_so

                            item.itemName.contains("근원을 삼킨 대검") -> R.drawable.swordgirl_one_big
                            item.itemName.contains("얼어붙은 저항의 대검") -> R.drawable.swordgirl_call_big
                            item.itemName.contains("내딛은 자의 대검") -> R.drawable.swordgirl_none_big
                            item.itemName.contains("광폭화된 전의의 대검") -> R.drawable.swordgirl_gawn_big
                            item.itemName.contains("사멸하는 신뢰의 대검") -> R.drawable.swordgirl_posion_big
                            item.itemName.contains("火 : 불타는 고난의 대검") -> R.drawable.swordgirl_fire_big
                            item.itemName.contains("水 : 오염된 눈의 대검") -> R.drawable.swordgirl_water_big
                            item.itemName.contains("木 : 그늘진 새벽의 대검") -> R.drawable.swordgirl_tree_big
                            item.itemName.contains("金 : 각인된 상처의 대검") -> R.drawable.swordgirl_gold_big
                            item.itemName.contains("土 : 따뜻한 봄날의 대검") -> R.drawable.swordgirl_mok_big
                            item.itemName.contains("부조화 : 무너진 경계의 대검") -> R.drawable.swordgirl_drak_big
                            item.itemName.contains("불가침의 영역 - 대검") -> R.drawable.swordgirl_bull_big

                            item.itemName.contains("근원을 삼킨 둔기") -> R.drawable.swordgirl_one_ham
                            item.itemName.contains("얼어붙은 저항의 둔기") -> R.drawable.swordgirl_call_ham
                            item.itemName.contains("내딛은 자의 둔기") -> R.drawable.swordgirl_none_ham
                            item.itemName.contains("광폭화된 전의의 둔기") -> R.drawable.swordgirl_gawn_ham
                            item.itemName.contains("사멸하는 신뢰의 둔기") -> R.drawable.swordgirl_posion_ham
                            item.itemName.contains("火 : 불타는 고난의 둔기") -> R.drawable.swordgirl_fire_ham
                            item.itemName.contains("水 : 오염된 눈의 둔기") -> R.drawable.swordgirl_water_ham
                            item.itemName.contains("木 : 그늘진 새벽의 둔기") -> R.drawable.swordgirl_tree_ham
                            item.itemName.contains("金 : 각인된 상처의 둔기") -> R.drawable.swordgirl_gold_ham
                            item.itemName.contains("土 : 따뜻한 봄날의 둔기") -> R.drawable.swordgirl_mok_ham
                            item.itemName.contains("부조화 : 무너진 경계의 둔기") -> R.drawable.swordgirl_drak_ham
                            item.itemName.contains("불가침의 영역 - 둔기") -> R.drawable.swordgirl_bull_ham

                            item.itemName.contains("근원을 삼킨 광검") -> R.drawable.swordgirl_one_bim
                            item.itemName.contains("얼어붙은 저항의 광검") -> R.drawable.swordgirl_call_bim
                            item.itemName.contains("내딛은 자의 광검") -> R.drawable.swordgirl_none_bim
                            item.itemName.contains("광폭화된 전의의 광검") -> R.drawable.swordgirl_gawn_bim
                            item.itemName.contains("사멸하는 신뢰의 광검") -> R.drawable.swordgirl_posion_bim
                            item.itemName.contains("火 : 불타는 고난의 광검") -> R.drawable.swordgirl_fire_bim
                            item.itemName.contains("水 : 오염된 눈의 광검") -> R.drawable.swordgirl_water_bim
                            item.itemName.contains("木 : 그늘진 새벽의 광검") -> R.drawable.swordgirl_tree_bim
                            item.itemName.contains("金 : 각인된 상처의 광검") -> R.drawable.swordgirl_gold_bim
                            item.itemName.contains("土 : 따뜻한 봄날의 광검") -> R.drawable.swordgirl_mok_bim
                            item.itemName.contains("부조화 : 무너진 경계의 광검") -> R.drawable.swordgirl_drak_bim
                            item.itemName.contains("불가침의 영역 - 광검") -> R.drawable.swordgirl_bull_bim

                            else -> R.drawable.swordgirl_defalut

                      }

                        Text(
                            text = item.slotName,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(
                                    top = 10.dp,
                                    bottom = 5.dp,
                                    end = 5.dp,
                                    start = 20.dp
                                )

                        )
                        Image(
                            painter = painterResource(id = imageResource),
                            contentDescription = "",
                            modifier = Modifier.padding(
                                start = 5.dp,
                                top = 10.dp,
                                end = 5.dp,
                                bottom = 10.dp
                            ).size(width = 30.dp, height = 30.dp)
                        )
                    }
                    Column(
                        modifier = Modifier.padding(
                            start = 10.dp,
                            top = 10.dp,
                            end = 5.dp,
                            bottom = 10.dp
                        )
                    ) {
                        Text(
                            text = "${item.itemName}",
                            color = Color.Blue,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Right,
                        )

                        Log.d("TAG", item.itemName)
                        if(item.upgradeInfo?.itemName!=null) {
                            Text(
                                text = item.upgradeInfo.itemName,
                                color = Color.Red,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                textAlign = TextAlign.Right
                            )
                            val enchantName = item.enchant?.status?.map { it.name }?.toMutableList()
                            var enchantExplain = item.enchant?.explain
                            if (enchantExplain == null) {
                                enchantExplain = ""
                            }

                            val enchantValue = item.enchant?.status?.map { it.value }

                            val combinedString = StringBuilder()

                            if (enchantName != null) {
                                for (i in enchantName.indices) {
                                    combinedString.append("${enchantName[i]} + ${enchantValue?.get(i)} " + "\n")
                                }
                            }
                        }
                    }
                    Text(
                        text = "${"+"}${item.reinforce}",
                        color = Color.Black,
                        fontWeight = FontWeight.Black,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(
                            start = 5.dp,
                            top = 12.dp,
                            end = 10.dp,
                            bottom = 5.dp
                        )
                    )

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