package com.example.hello_world

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.hello_world.ui.theme.Hello_worldTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import java.io.File
import coil.compose.rememberAsyncImagePainter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Hello_worldTheme {
                GreetingPreview()
            }
        }
    }
}

@Composable
fun LoadImage(ImageURI: String, modifier: Modifier = Modifier) {
    Image(
        painter = if (LocalInspectionMode.current) {
            painterResource(id = R.drawable.placeholder)
        } else {
            rememberAsyncImagePainter(ImageURI)
        },
        contentDescription = "Image",
        contentScale = ContentScale.Fit,
        modifier = modifier
    )
}

@Composable
fun IconText(IconURI: String, name: String) {
    val density = LocalDensity.current
    var textHeightPx by remember { mutableIntStateOf(0) }

    Row(modifier = Modifier.height(IntrinsicSize.Min), verticalAlignment = Alignment.CenterVertically) {
        LoadImage(IconURI, Modifier
                .height(with(density) { textHeightPx.toDp() })
                .aspectRatio(1f)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = "$name",
            fontSize = 15.sp,
            modifier = Modifier
                .padding(all = 8.dp)
                .fillMaxHeight(),
            onTextLayout = { textLayoutResult ->
                textHeightPx = textLayoutResult.size.height + density.run { 2.dp.toPx() }.toInt()
            }
        )

    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun GreetingPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xF0B6FFB7))
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoadImage(
                    "file:///android_asset/android_logo.png", Modifier.size(200.dp).background(
                        color = Color(
                            0xFF23243A
                        )
                    )
                )
                Text("Name Name", fontSize = 45.sp, fontFamily = FontFamily.Cursive)
                Text(
                    "Cool text example here",
                    fontSize = 20.sp,
                    color = Color(0xFF026027),
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column() {
                IconText("file:///android_asset/phone.png", "8 800 555 35 35")
                IconText("file:///android_asset/email.png", "email@example.com")
                IconText("file:///android_asset/phone.png", "something other")
                IconText("file:///android_asset/email.png", "etc.")
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}