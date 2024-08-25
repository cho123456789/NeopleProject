package com.example.myapplication

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresExtension
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.CharacterSearchScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    MyApp()
                }
            }
        }

    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    @Composable
    fun MyApp() {
        CharacterSearchScreen()
    }
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Preview(showBackground = true)
@Composable
   fun PreviewMainContainer() {
        MaterialTheme{
            MyApp()
        }
    }
}
