package com.example.compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
                // Surface 로 감싸주는 것이 좋다.
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    AnimationEx()
                }
            }
        }
    }
}


@Composable
fun AnimationEx() {
    var isTextVisible by remember { mutableStateOf(true) }
    var isBackgroundWhite by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        Text(text = "Hello World!")
        Row {
            RadioButton(
                selected = true, // text 보이게 하기
                onClick = { isTextVisible = true }
            )
            Text(
                text = "Hello World Appears",
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        Row {
            RadioButton(
                selected = false, // text 안보이게 하기
                onClick = { isTextVisible = false }
            )
            Text(
                text = "Hello World Disappears",
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        Text(text = "Change the background color")
        Row {
            RadioButton(selected = true, onClick = { isBackgroundWhite = true })
            Text(
                text = "White",
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        Row {
            RadioButton(selected = false, onClick = { isBackgroundWhite = false })
            Text(
                text = "Red",
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePracticeTheme {
        AnimationEx()
    }
}