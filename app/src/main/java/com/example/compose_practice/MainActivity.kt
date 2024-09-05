package com.example.compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
                RowEx()
            }
        }
    }
}

@Composable
fun RowEx() {
    Row(
        modifier = Modifier.height(60.dp).width(250.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Text(text = "첫번째!", modifier = Modifier.align(Alignment.Top).weight(1f))
        Text(text = "두번째!", modifier = Modifier.weight(2f))
        Text(text = "세번째!", modifier = Modifier.weight(4f))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePracticeTheme {
        RowEx()
    }
}