package com.example.myapplication
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.theme.MyApplicationTheme


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ServerListScreen()
        }
    }

    @Composable
    fun ServerListScreen() {
        val serverViewModel: ServerViewModel = viewModel()
        val context = LocalContext.current

        LaunchedEffect(Unit) {
            serverViewModel.getServers()
        }
        serverViewModel.serverNames.observe(this@MainActivity, { serNames ->
            serNames?.let {
                Log.d("TAG", it.toString())
            }
        })
        serverViewModel.serverId.observe(this@MainActivity,{
            serverId -> serverId?.let{
            Log.d("TAG", it.toString())
        }
        })
    }
}
    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        MyApplicationTheme {

        }
}