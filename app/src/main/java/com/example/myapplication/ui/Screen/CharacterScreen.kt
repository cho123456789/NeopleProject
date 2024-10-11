package com.example.myapplication.ui.Screen

import CharacterSettingScreen
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.viewmodel.CharacterEquipmentViewModel
import com.example.myapplication.viewmodel.CharacterImageViewModel
import com.example.myapplication.viewmodel.CharacterInfoViewModel
import com.example.myapplication.viewmodel.CharacterSettingViewModel
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterialApi::class)
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun CharacterSearchScreen(
    navController: NavController,
    viewModel: CharacterInfoViewModel = hiltViewModel(),
    viewModelSetting: CharacterSettingViewModel = hiltViewModel(),
    viewModelImage: CharacterImageViewModel = hiltViewModel(),
    viewModelEquipment: CharacterEquipmentViewModel = hiltViewModel()
) {
    //val characterId by viewModel.character.collectAsState()

    val characterIds by viewModel.characterId.collectAsState(initial = emptyList())
    val equipment by viewModelEquipment.equipment.collectAsState(initial = emptyList())
    val jobGrowNameIds by viewModelSetting.jobGrowName.collectAsState()
    val GuildNameIds by viewModelSetting.guildName.collectAsState()
    val characterNameIds by viewModelSetting.characterName.collectAsState()
    val adventureNameIds by viewModelSetting.adventureName.collectAsState()
    val profileImg by viewModelImage.imageBitmap.collectAsState()


    val characterName = characterNameIds
    val jobGrowName = jobGrowNameIds
    val GuildName = GuildNameIds
    val adventureName = adventureNameIds
    val characterId = characterIds.joinToString()

    var inputCharacterId by remember { mutableStateOf("") }
    var inputServerId by remember { mutableStateOf("") }

    var selectedOption by remember { mutableStateOf("option1") }
    var expanded by remember { mutableStateOf(false) }

    val options = listOf(
        "cain" to "카인",
        "diregie" to "디레지에",
        "siroco" to "시로코",
        "prey" to "프레이",
        "casillas" to "카시야스",
        "hilder" to "힐더",
        "anton" to "안톤",
        "바칼" to "bakal"
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
                    .padding(5.dp)
            ) {
                //Text("선택된 값: $selectedOption")  // 선택된 값 출력

                OutlinedButton(
                    modifier = Modifier.
                        wrapContentWidth()
                        .height(50.dp)
                        .padding(5.dp),

                    onClick = { expanded = true }
                ) {
                    Text(options.find { it.first == selectedOption }?.second ?: "")
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = "Arrow Down")
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false
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
                    value = inputCharacterId,
                    onValueChange = { inputCharacterId = it },
                    label = { Text("캐릭터 이름 입력") },
                    modifier = Modifier.
                                wrapContentSize(),
                    singleLine = true,
                    trailingIcon = {
                        IconButton(onClick = { /* 검색 로직 */ }) {
                            Icon(Icons.Default.Search, contentDescription = null)
                        }
                    }
                )

            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        getServerIdById(inputServerId)?.let {
                            viewModel.getCharacterInfo(
                                it,
                                inputCharacterId
                            )
                        }
//                    Log.d("Screen1",serverId + inputCharacterId)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Search")
                    getServerIdById(inputServerId)?.let {
                        viewModelSetting.getCharacterSetting(
                            it,
                            characterId
                        )
                    }
                    getServerIdById(inputServerId)?.let {
                        viewModelImage.getCharacterImage(
                            it,
                            characterId
                        )
                    }
                    getServerIdById(inputServerId)?.let {
                        viewModelEquipment.getCharacterEquipment(
                            it,
                            characterId
                        )
                    }
                }
            }
        }
    }


//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(5.dp),
//        verticalArrangement = Arrangement.Top,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Spacer(modifier = Modifier.height(10.dp))
//
//        TextField(
//            value = inputCharacterId,
//            onValueChange = { inputCharacterId = it },
//            label = { Text("Character Name") },
//            modifier = Modifier.fillMaxWidth(),
//            singleLine = true
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        TextField(
//            value = inputServerId,
//            onValueChange = { inputServerId = it },
//            label = { Text("Server ID") },
//            modifier = Modifier.fillMaxWidth(),
//            singleLine = true,
//            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
//            trailingIcon = {
//                IconButton(onClick = { /* 검색 로직 */ }) {
//                    Icon(Icons.Default.Search, contentDescription = null)
//                }
//            }
//        )
//
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(
//            onClick = {
//                getServerIdById(inputServerId)?.let {
//                    viewModel.getCharacterInfo(
//                        it,
//                        inputCharacterId
//                    )
//                }
////                    Log.d("Screen1",serverId + inputCharacterId)
//            },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("Search")
//            getServerIdById(inputServerId)?.let {
//                viewModelSetting.getCharacterSetting(
//                    it,
//                    characterId
//                )
//            }
//            getServerIdById(inputServerId)?.let {
//                viewModelImage.getCharacterImage(
//                    it,
//                    characterId
//                )
//            }
//            getServerIdById(inputServerId)?.let {
//                viewModelEquipment.getCharacterEquipment(
//                    it,
//                    characterId
//                )
//            }
//        }
//        Spacer(modifier = Modifier.height(10.dp))
//        profileImg?.let {
//            CharacterSettingScreen(
//                adventureName,
//                characterName,
//                inputServerId,
//                jobGrowName,
//                GuildName,
//                it
//            )
//        }
//        Box(
//            modifier = Modifier
//                .padding(10.dp)  // 상단에 약간의 간격 추가
//        ) {
//            Button(
//                onClick = {
//                    Log.d("Equiment", equipment.toString())
//                    val equipmentListJson = Gson().toJson(equipment)
//                    val encodedEquipmentListJson =
//                        URLEncoder.encode(equipmentListJson, StandardCharsets.UTF_8.toString())
//                    // "equipment" 경로로 이동
//                    navController.navigate("equipment/$encodedEquipmentListJson")
//                },
//            ) {
//                Text("장착장비")
//            }
//        }
//    }
}

fun getServerIdById(serverId: String): String? {
    val serverMap = mapOf(
        "카인" to "cain",
        "디레지에" to "diregie",
        "시로코" to "siroco",
        "프레이" to "prey",
        "카시야스" to "casillas",
        "힐더" to "hilder",
        "안톤" to "anton",
        "바칼" to "bakal"
    )
    return serverMap[serverId]
}