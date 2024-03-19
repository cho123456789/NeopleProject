package com.example.myapplication
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    ComposeDropdownMenuExample()
                }
            }
        }
    }

    @Composable
    fun DropdownMenuExample(items: List<Pair<String, String>>) {
        val serverViewModel: ServerViewModel = viewModel()
        var expanded by remember { mutableStateOf(false) }
        var selectedIndex by remember { mutableStateOf(0) }
        var serverId by remember { mutableStateOf("") }

        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.padding(16.dp).fillMaxWidth()
                ) {
                    items.forEachIndexed { index, item ->
                        DropdownMenuItem(
                            text = { Text(text = item.second) },
                            onClick = {
                                selectedIndex = index
                                expanded = false
                                serverId = item.first
                            }
                        )
                    }
                }
                Button(
                    modifier = Modifier.padding(top = 5.dp),
                    onClick = { expanded = true }) {
                    Text(text = "드롭다운 열기")
                }
                val selectedKorean = items[selectedIndex].second
                // 선택된 아이템 표시
                Text(
                    text = "선택한 서버: ${selectedKorean}",
                    modifier = Modifier.padding(16.dp)
                )
            }
            SimpleOutlinedTextFieldSample(serverId,serverViewModel)
        }
    }

    @Composable
    fun ComposeDropdownMenuExample() {
        val items = listOf(
            Pair("cain", "카인"),
            Pair("diregie", "디레지에"),
            Pair("siroco", "시로코"),
            Pair("prey", "프레이"),
            Pair("casillas", "카시야스"),
            Pair("hilder", "힐더"),
            Pair("anton", "안톤"),
            Pair("bakal", "바칼")
        )
        DropdownMenuExample(items = items)
    }

    @Composable
    fun SimpleOutlinedTextFieldSample(serverId: String ,serverViewModel: ServerViewModel) {
        var characterName by remember { mutableStateOf("") }
        var characterId by remember { mutableStateOf("") }

        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            OutlinedTextField(
                value = characterName,
                onValueChange = { characterName = it },
                label = { Text("캐릭터 이름 입력") },
                maxLines = 1
            )
            Button(
                modifier = Modifier.padding(top = 10.dp, start = 10.dp),
                onClick = {
                    serverViewModel.getCharacter(serverId, characterName)
                }
            ) {
                Text("확인")
            }
        }
        CharacterInfoTextView(serverId,serverViewModel)
    }
    @Composable
    fun CharacterInfoTextView(
        serverId: String,
        serverViewModel: ServerViewModel
    ) {
        val characterName by serverViewModel.characterName.collectAsState()
        val characterId by serverViewModel.characterId.collectAsState()
        val characterLevel by serverViewModel.level.collectAsState()
        val jobGrowName by serverViewModel.jobGrowName.collectAsState()

        Log.d("TAG","ServerId"+ "${serverId} , characterId" + characterId)

        serverViewModel.getCharacterImg(serverId, characterId.toString(), "1")

        Column(
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text("Character Name: $characterName")
            Text("Character ID: $characterId")
            Text("Character Level: $characterLevel")
            Text("Job Grow Name: $jobGrowName")
//            LoadCharacterImage(
//                serverId= serverId,
//                characterId = characterId,
//                zoom = "1",
//                serverViewModel = serverViewModel
//                )
        }
    }
    @Composable
    fun LoadCharacterImage(
        serverId: String,
        characterId :String,
        zoom : String,
        serverViewModel: ServerViewModel
    )
    {

    }
    @Composable
    fun LoadBitmapImage(bitmap: ImageBitmap) {
        val painter: Painter = BitmapPainter(bitmap)

        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painter,
            contentDescription = null // 이미지에 대한 설명이 필요한 경우 여기에 추가
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        // Preview 코드 추가
    }
}