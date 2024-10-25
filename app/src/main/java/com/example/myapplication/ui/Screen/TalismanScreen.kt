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
import com.data.remote.dto.Talisman
import com.data.remote.dto.TalismanDto
import com.data.remote.dto.TalismanWithRunes
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
import com.example.myapplication.viewmodel.TalismanViewModel

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun TalismanScreen(
    navController: NavController,
    //equipmentList: List<BufferEquipment>
    viewModel: TalismanViewModel = hiltViewModel(),

    ) {
    val talismans by viewModel.talismansItem.collectAsState(initial = emptyList())
    val context = LocalContext.current
    val (characterId, serverId) = getCharacterId(context)

    LaunchedEffect(key1 = navController.currentBackStackEntry) {
        if (characterId != null && serverId != null) {
            Log.d("Character ID", "Retrieved Character ID: $characterId")
            Log.d("Server ID", "Retrieved Server ID: $serverId")
            viewModel.getTalismans(serverId, characterId)

        }
    }
    if (talismans.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(), // 화면 전체를 차지
            contentAlignment = Alignment.Center // 가운데 정렬
        ) {
            CircularProgressIndicator() // 가운데에 배치
        }
    } else {
        Log.d("AvatarItemScreen", talismans.toString())

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
                items(talismans) { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                    ) {
                        TalismanCard(item)
                    }
                }
            }
            BottomNavigationBar(navController = navController)
        }
    }
}

@Composable
fun TalismanCard(talismanItem: TalismanWithRunes) {
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
                    Column(
                        Modifier.fillMaxSize()
                    )
                    {
                        Column(
                            Modifier.wrapContentSize()
                                .padding(10.dp)
                        )
                        {
                            Text(
                                text = talismanItem.talisman.itemName,
                                fontWeight = FontWeight.Bold,
                                color = Color.Magenta,
                                fontSize = 20.sp
                            )
                        }
                        Column(
                            Modifier.fillMaxSize()
                                .padding(10.dp),
                            horizontalAlignment = Alignment.End
                        ) {
                            talismanItem.runes.forEach { rune ->
                                if(rune.itemName.contains("테라코타")) {
                                    Text(
                                        text = rune.itemName,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Magenta,
                                        fontSize = 15.sp
                                    )
                                }
                                else if(rune.itemName.contains("세컨드 팩트")){
                                    Text(
                                        text = rune.itemName,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Red,
                                        fontSize = 15.sp
                                    )
                                }
                                else if(rune.itemName.contains( "수호자들")){
                                    Text(
                                        text = rune.itemName,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Blue,
                                        fontSize = 15.sp
                                    )
                                }
                                else if(rune.itemName.contains( "서클 메이지")){
                                    Text(
                                        text = rune.itemName,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Green,
                                        fontSize = 15.sp
                                    )
                                }
                                else if(rune.itemName.contains( "고대 도서관")){
                                    Text(
                                        text = rune.itemName,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Yellow,
                                        fontSize = 15.sp
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