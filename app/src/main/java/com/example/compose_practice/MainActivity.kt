package com.example.compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
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
            ComposePracticeTheme {
                Outer()
            }
        }
    }
}

@Composable
fun Outer() {
    Column(modifier = Modifier.width(100.dp)) {
        Inner(
            modifier = Modifier
                .width(300.dp)
                .height(300.dp)
        )
        Inner(
            modifier = Modifier
                .width(300.dp)
                .height(300.dp)
        )
    }
}

@Composable
private fun Inner(modifier: Modifier = Modifier) {
    BoxWithConstraints(modifier) {
        if (maxHeight > 40.dp) {
            Text(
                text = "maxHeight 가 40dp 를 넘어요",
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
        Text("maxWidth: $maxWidth, maxHeight: $maxHeight, minWidth: $minWidth, minHeight: $minHeight")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePracticeTheme {
        Outer()
    }
}