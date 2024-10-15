package com.example.myapplication.ui.Screen

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.data.remote.room.AppDatabase
import com.example.myapplication.R
import com.example.myapplication.viewmodel.CharacterEquipmentViewModel
import com.example.myapplication.viewmodel.CharacterInfoViewModel
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@SuppressLint("MutableCollectionMutableState")
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun CharacterSearchScreen(
    navController: NavController,
    viewModel: CharacterInfoViewModel = hiltViewModel(),
    viewModelEquipment: CharacterEquipmentViewModel = hiltViewModel()
) {

    val characterIds by viewModel.characterId.collectAsState(initial = emptyList())
    val equipment by viewModel.equipment.collectAsState(initial = emptyList())
    val jobGrowNameIds by viewModel.jobGrowName.collectAsState()
    val GuildNameIds by viewModel.guildName.collectAsState()
    val characterNameIds by viewModel.characterName.collectAsState()
    val adventureNameIds by viewModel.adventureName.collectAsState()
    val profileImg by viewModel.imageBitmap.collectAsState()
    val imgCheck by viewModel.ImageCheck.collectAsState()

    val characterId = characterIds.joinToString()

    var inputCharacterName by remember { mutableStateOf("") }
    var inputServerId by remember { mutableStateOf("") }

    var selectedOption by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }


    val options = listOf(
        "cain" to "카인",
        "diregie" to "디레지에",
        "siroco" to "시로코",
        "prey" to "프레이",
        "casillas" to "카시야스",
        "hilder" to "힐더",
        "anton" to "안톤",
        "bakal" to "바칼"
    )

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    )
    {
        Column(
            modifier = Modifier
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(7.dp)
            ) {
                //Text("선택된 값: $selectedOption")  // 선택된 값 출력

                OutlinedButton(
                    modifier = Modifier
                        .wrapContentWidth()
                        .height(67.dp)
                        .padding(5.dp)
                        .border(1.dp, Color.Black, RoundedCornerShape(2.dp)),
                    onClick = { expanded = true }
                ) {
                    Text(
                        options.find { it.first == selectedOption }?.second ?: "",
                        modifier = Modifier.wrapContentSize(),
                        color = Color.Black
                    ) // 원하는 폭으로 고정
                    Icon(
                        Icons.Filled.ArrowDropDown,
                        contentDescription = "Arrow Down",
                        tint = Color.Black
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    }
                ) {
                    options.forEach { option ->
                        DropdownMenuItem(onClick = {
                            selectedOption = option.first  // 영어 값 저장
                            expanded = false
                        }) {
                            Text(option.second)  // 한글 표시
                        }
                    }
                }
                TextField(
                    value = inputCharacterName,
                    onValueChange = { inputCharacterName = it },
                    label = { Text("캐릭터 이름 입력", color = Color.Gray) },
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth()
                        .border(1.dp, Color.Black),
                    singleLine = true,
                    trailingIcon = {
                        IconButton(onClick = {
                            viewModel.getCharacterInfo(selectedOption, inputCharacterName)
                            // 서버 ID로 캐릭터 정보 가져오기
                            //viewModelImage.getCharacterImage(selectedOption, characterId)

                            viewModel.addCharacter(
                                characterId = characterId,
                                inputServerId = selectedOption,
                                characterNameIds = inputCharacterName
                            )

                        }) {
                            Icon(Icons.Default.Search, contentDescription = null)
                        }
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White
                    )
                )
            }
            // 캐릭터 리스트
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                itemsIndexed(viewModel.characters.value) { index, character ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                Log.d("character.characterServer", character.characterServer)
                                viewModel.getCharacterInfo(
                                    character.characterServer,
                                    character.characterName
                                )
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "[" + getServerString(character.characterServer) + "]" + " " + character.characterName)
                        Row(
                            Modifier.padding(
                                5.dp
                            )
                        ) {
                            OutlinedButton(
                                onClick = {
                                    val equipmentListJson = Gson().toJson(equipment)
                                    val encodedEquipmentListJson = URLEncoder.encode(
                                        equipmentListJson,
                                        StandardCharsets.UTF_8.toString()
                                    )
                                    navController.navigate("장착장비/$encodedEquipmentListJson")
                                    //navController.navigate("main")
                                },
                                border = BorderStroke(1.dp, Color.Blue),
                                shape = RoundedCornerShape(50), // = 50% percent
                                // or shape = CircleShape
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Blue)
                            ) {
                                Text(
                                    text = "장비 조회",
                                    fontSize = 12.sp,
                                    color = Color.Blue,
                                    fontWeight = FontWeight.W100
                                )
                            }
                            IconButton(onClick = {
                                viewModel.deleteCharacter(character.id)
                                viewModel.resetImageCheck()
                            }) {
                                Icon(Icons.Default.Delete, contentDescription = "삭제")
                            }
                        }
                    }
                }
            }
            if (imgCheck) {
                profileImg?.let {
                    CharacterSettingScreen(
                        adventureNameIds,
                        characterNameIds.toString(),
                        inputServerId,
                        jobGrowNameIds,
                        GuildNameIds,
                        it
                    )
                }
            }
        }
    }
}

fun getServerString(serverId: String): String? {
    val serverMap = mapOf(
        "cain" to "카인",
        "diregie" to "디레지에",
        "siroco" to "시로코",
        "prey" to "프레이",
        "casillas" to "카시야스",
        "hilder" to "힐더",
        "anton" to "안톤",
        "bakal" to "바칼"
    )
    return serverMap[serverId]
}