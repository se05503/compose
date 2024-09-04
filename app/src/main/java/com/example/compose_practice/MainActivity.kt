package com.example.compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_practice.ui.theme.ComposePracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // setContent 안에 모든 코드를 집어넣는 것은 좋지 않다.
            ComposePracticeTheme {
                BoxEx()
            }
        }
    }
}

// 실제로 코드를 주로 작성하는 곳
@Composable
fun BoxEx() {
    Box(modifier = Modifier.size(100.dp)) {
        Text(text = "Compose", modifier = Modifier.align(Alignment.Center))
        Text(text = "Coffee", modifier = Modifier.align(Alignment.BottomEnd))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePracticeTheme {
        BoxEx()
    }
}