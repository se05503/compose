package com.example.compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_practice.ui.theme.ComposePracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // setContent 안에 모든 코드를 집어넣는 것은 좋지 않다.
            ComposePracticeTheme {
                Greeting("Android")
            }
        }
    }
}

// 실제로 코드를 주로 작성하는 곳
@Composable
fun Greeting(text: String) {
    Surface(
        modifier = Modifier.padding(15.dp),
        elevation = 5.dp,
        border = BorderStroke(width = 2.dp, color = Color.Red),
        shape = CircleShape
    ) {
        Text(
            text = "Hello $text!",
            modifier = Modifier.padding(8.dp)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePracticeTheme {
        Greeting(text = "세영")
    }
}