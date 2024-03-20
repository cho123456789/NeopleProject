package com.example.myapplication
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


class MainActivity : AppCompatActivity() {
    private val serverViewModel: ServerViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    ComposeDropdownMenuExample(serverViewModel)
                }
            }
        }
    }

    @Composable
    fun DropdownMenuExample(items: List<Pair<String, String>>, serverViewModel: ServerViewModel) {
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
            SimpleOutlinedTextFieldSample(serverId, serverViewModel)
        }
    }

    @Composable
    fun ComposeDropdownMenuExample(serverViewModel: ServerViewModel) {
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
        DropdownMenuExample(items = items, serverViewModel)
    }

    @Composable
    fun SimpleOutlinedTextFieldSample(serverId: String, serverViewModel: ServerViewModel) {
        var characterName by remember { mutableStateOf("") }
        var showCharacterInfo by remember { mutableStateOf(false) } // 상태 추가

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = characterName,
                onValueChange = { characterName = it },
                label = { Text("캐릭터 이름 입력") },
                maxLines = 1
            )
            Button(
                modifier = Modifier.padding(top = 10.dp, start = 10.dp),
                onClick = {
                    showCharacterInfo = true
                }
            ) {
                Text("확인")
            }
        }
        if(showCharacterInfo) {
            CharacterInfoTextView(
                serverViewModel = serverViewModel,
                serverId = serverId)
            serverViewModel.getCharacter(serverId,characterName)
        }
    }

    @Composable
    fun CharacterInfoTextView(
        serverViewModel: ServerViewModel,
        serverId: String
    ) {
        val characterName by serverViewModel.characterName.collectAsState()
        val characterId by serverViewModel.characterId.collectAsState()
        val characterLevel by serverViewModel.level.collectAsState()
        val jobGrowName by serverViewModel.jobGrowName.collectAsState()
        val bitmap by serverViewModel.imageBitmap.collectAsState()
        val painter = bitmap?.let { BitmapPainter(it) }

        serverViewModel.getCharacterImg(serverId,characterId.joinToString(", "),"1")
        Column(
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text("Character Name: ${characterName.joinToString(", ")}")
            Text("Character ID: ${characterId.joinToString(", ")}")
            Text("Character Level: ${characterLevel.joinToString(", ")}")
            Text("Job Grow Name: ${jobGrowName.joinToString(", ")}")
            bitmap?.let {
                if (painter != null) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        alignment = Alignment.Center,
                        painter = painter,
                        contentDescription = null // 이미지에 대한 설명이 필요한 경우 여기에 추가
                    )
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        // Preview 코드 추가
    }
}