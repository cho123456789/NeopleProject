package com.example.myapplication
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch


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
    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    fun ServerListScreen() {
        val serverViewModel: ServerViewModel = viewModel()
        val context = LocalContext.current

        LaunchedEffect(Unit) {
            serverViewModel.getServers()
        }
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED)
            {
                launch {
                    serverViewModel.serverNames.collect {
                        Log.d("TAG", it.toString())
                    }
                }
                launch {
                    serverViewModel.serverId.collect {
                        Log.d("TAG", it.toString())
                    }
                }

            }
        }
}@Composable
    fun DropdownMenuExample(items: List<Pair<String, String>>) {
        var expanded by remember { mutableStateOf(false) }
        var selectedIndex by remember { mutableStateOf(0) }

        val koreanToEnglishMap = mapOf(
            "카인" to "cain",
            "디레지에" to "diregie",
            "시로코" to "siroco",
            "프레이" to "prey",
            "카시야스" to "casillas",
            "힐더" to "hilder",
            "안톤" to "anton",
            "바칼" to "bakal"
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            // 드롭다운 버튼
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.padding(16.dp).fillMaxWidth()
            ) {
                items.forEachIndexed { index, item ->
                    DropdownMenuItem(
                        text = { Text(text = item.second)}, onClick = {
                        selectedIndex = index
                        expanded = false
                        Log.d("TAG", item.first)
                    })
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
        ServerListScreen()
    }
    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
    }
}