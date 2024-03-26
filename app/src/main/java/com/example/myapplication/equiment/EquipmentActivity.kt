package com.example.myapplication.equiment
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import com.database.characterDatabase
import com.dto.CharacterDto
import com.example.dao.CharacterDao
import com.example.myapplication.ServerViewModel


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
    @Composable
    fun OtherActivityContent(serverViewModel: ServerViewModel) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                ) {

                    val characterItem by serverViewModel.characterId.collectAsState()
                    val characterName by serverViewModel.characterName.collectAsState()

                    Log.d("TAG","characterItem : ${characterItem} " +
                            "" +"characterName : ${characterName} " )

                    val characterItemId = characterItem.joinToString(", ")
                    serverViewModel.getCharacterId("cain",characterName)
                    serverViewModel.getCharacterEquiment("cain",characterItemId)

                    val slotName by serverViewModel.slotName.collectAsState()
                    val itemType by serverViewModel.itemType.collectAsState()
                    val itemName by serverViewModel.itemName.collectAsState()



                    Log.d("TAG","slotName : ${slotName} " +
                            "" +"itemType : ${itemType} " +
                            "" +"itemName : ${itemName} "
                    )
                    Text(
                        text = slotName,
                        color = Color.Black
                    )
                    Text(text = itemType)
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