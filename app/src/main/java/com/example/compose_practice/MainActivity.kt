package com.example.compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter
import com.example.compose_practice.ui.theme.ComposePracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePracticeTheme {
                CoilEx()
            }
        }
    }
}

@Composable
fun CoilEx() {
    val painter = rememberImagePainter(data = "https://picsum.photos/300/300")
    // contentDescription : 접근성을 위해서 필요한 경우 꼭 넣어주기
    //
    Image(painter = painter, contentDescription = "네트워크 이미지")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePracticeTheme {
        CoilEx()
    }
}