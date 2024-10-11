package com.example.myapplication.ui.theme

import android.os.Build
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.common.Resource
import com.example.myapplication.R

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun HomeScreen(
    navController: NavController
){
    Column (
    ){
        Image(
            painter = painterResource(id = R.drawable.main),
            contentDescription = ""
        )
        Image(
            painter = painterResource(id = R.drawable.main2),
            contentDescription = ""
        )
        Button(
            modifier = Modifier.fillMaxWidth()
                .padding(20.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Yellow),
                shape = CircleShape,
            onClick = {
                navController.navigate("notifications")
            }
        ) {
            Text(
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                text = "내 캐릭터 조회"
            )

        }
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.logo_t1),
            contentDescription = ""
        )
    }
}