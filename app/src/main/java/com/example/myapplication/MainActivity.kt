package com.example.myapplication
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.equiment.EquipmentActivity


class MainActivity : AppCompatActivity() {
    private val serverViewModel: ServerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    MyApp(serverViewModel)
                }
            }
        }

    }


    @Composable
    fun MyApp(serverViewModel: ServerViewModel){
        val navController = rememberNavController()

        NavHost(navController , startDestination = "home"){
            composable("home"){
                DropdownMenuExample(
                    navController,
                    serverViewModel)
            }
            composable("other_activity"){
                EquipmentActivity().OtherActivityContent(serverViewModel)
            }
        }

    }

    @SuppressLint("SuspiciousIndentation")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DropdownMenuExample(
        navController: NavController,
        serverViewModel: ServerViewModel) {

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

        var serverItem by remember {
            mutableStateOf("")
        }

        var  showCharacterInfo by remember { mutableStateOf(false) } // 상태 업데이트


        var expanded by remember { mutableStateOf(false) }

        var selectedIndex by remember { mutableStateOf(0) }

        var serverId by remember { mutableStateOf("") }

        var characterName by remember { mutableStateOf("") }


        LaunchedEffect(Unit) {
            showCharacterInfo = true
        }

            Row(
                modifier = Modifier
                    .padding(
                        start = 5.dp,
                        top = 5.dp,
                        bottom = 10.dp,
                        end = 10.dp
                    )
            ) {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = it },

                    ) {
                    OutlinedTextField(
                        value = serverItem,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                        },
                        textStyle = TextStyle.Default.copy(
                            fontSize = 11.sp
                        ),
                        modifier = Modifier
                            .menuAnchor()
                            .padding()
                            .size(width = 119.dp, height = 64.dp)
                            .padding(start = 5.dp, top = 8.dp, end = 5.dp)
                    )
                    ExposedDropdownMenu(expanded = expanded, onDismissRequest = {/*TODO*/ }) {
                        items.forEachIndexed { index, item ->
                            DropdownMenuItem(
                                text = { Text(text = item.second) },
                                onClick = {
                                    selectedIndex = index
                                    serverItem = item.second
                                    expanded = false
                                    serverId = item.first
                                }
                            )
                        }
                    }
                }
            }
            OutlinedTextField(
                value = characterName,
                onValueChange = { characterName = it },
                label = { Text("캐릭터 이름 입력") },
                maxLines = 1,
                modifier = Modifier
                    .padding(
                        top = 5.dp,
                        end = 5.dp,
                        start = 125.dp
                    )
                    .wrapContentSize(),
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                trailingIcon = {
                    IconButton(onClick = { showCharacterInfo = true }) {
                        Icon(Icons.Default.Check, contentDescription = null)
                    }
                }
            )
            if (showCharacterInfo) {
                CharacterInfoTextView(
                    serverViewModel = serverViewModel,
                    navController = navController,
                    serverItem = serverItem)
                   serverViewModel.getCharacterId("cain", characterName)
            }

    }

    @Composable
    fun CharacterInfoTextView(
        serverViewModel: ServerViewModel,
        navController : NavController,
        serverItem : String
    ) {

        val characterName by serverViewModel.characterName.collectAsState()

        val jobName by serverViewModel.jobName.collectAsState()

        val characterId by serverViewModel.characterId.collectAsState()

        val characterLevel by serverViewModel.level.collectAsState()

        val jobGrowName by serverViewModel.jobGrowName.collectAsState()

        val adventureName by serverViewModel.adventureName.collectAsState()

        val bitmap by serverViewModel.imageBitmap.collectAsState()
        val painter = bitmap?.let { BitmapPainter(it) }

        val serverId  by serverViewModel.serverId.collectAsState()

        val borderColor = Color(android.graphics.Color.parseColor("#E2E2E2")) // 헥사 값으로 빨간색을 정의
        serverViewModel.getCharacter("cain", characterId.joinToString(", "))
        serverViewModel.getCharacterImg("cain", characterId.joinToString(", "), "1")
        serverViewModel.updateServerItem(serverItem)

        Log.d("JOBName : " ,  jobName)

        Row(
            modifier = Modifier
                .padding(
                    top = 90.dp,
                    start = 10.dp,
                    bottom = 10.dp,
                    end = 10.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(modifier = Modifier
                .border(width = 1.dp, color = borderColor, shape = RectangleShape)
                .fillMaxWidth()
                .clickable {
                    navController.navigate("other_activity")
                }
            ){
                val imageResource = when (jobName) {
                    "귀검사(남)" -> R.drawable.swordman
                    "귀검사(여)" -> R.drawable.swordgirl
                    "거너(남)" -> R.drawable.gunner_m
                    "거너(여)" -> R.drawable.gunner_g
                    "마법사(여)"->R.drawable.mage_g
                    "마법사(남)"->R.drawable.mage_m
                    "프리스트(남)"-> R.drawable.prist_m
                    "프리스트(여)"-> R.drawable.prist_g
                    "격투가(남)"->R.drawable.fighter_m
                    "격투가(여)"->R.drawable.fighter_g
                    "나이트"->R.drawable.knight
                    "도적"->R.drawable.theif
                    "다크나이트"->R.drawable.darknight
                    "크리에이터"->R.drawable.creature
                    "아처"->R.drawable.archer
                    "마창사"->R.drawable.lancer
                    "총검사"->R.drawable.gunblade
                    else -> R.drawable.defalut // 기본 이미지 설정
                }

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
                Text(
                    modifier = Modifier.padding(
                        start = 70.dp,
                        top = 12.dp
                    ),
                    textAlign = TextAlign.Center,
                    text = characterName + " / " + "Lv" +characterLevel + " / " + jobGrowName
                    )
            }
        }

//        Column(
//            modifier = Modifier
//                .padding(vertical = 8.dp)
//                .padding(
//                    top = 90.dp,
//                    start = 50.dp
//                )
//        ) {
//            Box(
//                modifier = Modifier
//                    .size(width = 300.dp, height = 600.dp)
//                    .border(width = 1.dp, color = borderColor, shape = RectangleShape)
//                    .align(Alignment.CenterHorizontally)
//            ) {
//                Column(
//                ) {
//                    bitmap?.let {
//                        if (painter != null) {
//                            Image(
//                                modifier = Modifier
//                                    .wrapContentSize()
//                                    .size(width = 300.dp, height = 250.dp),
//                                alignment = Alignment.Center,
//                                painter = painter,
//                                contentDescription = null // 이미지에 대한 설명이 필요한 경우 여기에 추가
//                            )
//                        }
//                        Text(
//                            text = adventureName,
//                            modifier = Modifier.padding(
//                                start = 100.dp, end = 5.dp
//                            ),
//                            fontSize = 20.sp,
//                            textAlign = TextAlign.Center,
//                            color = Color.Blue
//                        )
//                        Text(
//                            text = characterName,
//                            modifier = Modifier.padding(
//                                start = 100.dp, end = 5.dp
//                            ),
//                            fontSize = 30.sp,
//                            textAlign = TextAlign.Center
//                        )
//                        Text(
//                            text = jobGrowName,
//                            modifier = Modifier.padding(
//                                start = 100.dp, end = 5.dp
//                            ),
//                            fontSize = 20.sp,
//                            textAlign = TextAlign.Center,
//                            color = Color.Gray
//                        )
//                    }
//                    Row(
//                        modifier = Modifier
//                            .padding(10.dp)
//                            .align(Alignment.CenterHorizontally)
//                    ) {
//                        Button(
//                            onClick = {
//                                navController.navigate("other_activity")
//                            } ,
//                            contentPadding = PaddingValues(12.dp),
//                            modifier = Modifier.wrapContentSize()
//                        ) {
//                            Text("장비")
//                        }
//
//                        Button(
//                            onClick = {},
//                            contentPadding = PaddingValues(12.dp),
//                            modifier = Modifier
//                                .wrapContentSize()
//                                .padding(start = 10.dp)
//                        ) {
//                            Text("아바타")
//                        }
//                        Button(
//                            onClick = {},
//                            contentPadding = PaddingValues(12.dp),
//                            modifier = Modifier
//                                .wrapContentSize()
//                                .padding(start = 10.dp)
//                        ) {
//                            Text("크리쳐")
//                        }
//                    }
//                }
//            }
//        }
    }
    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
            MaterialTheme {

            }
        }
}