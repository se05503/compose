package com.example.compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
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


@OptIn(ExperimentalAnimationApi::class) // OptIn : 사용하겠다, OptOut : 사용하지 않겠다
@Composable
fun AnimationEx() {
    var isTextVisible by remember { mutableStateOf(true) }
    var isBackgroundWhite by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        AnimatedVisibility(
            visible = isTextVisible,
            enter = slideInVertically()
        ) {
            Text(text = "Hello World!")
        }
        Row(
            // Row의 Modifier에서 selectable 속성으로 해당 row를 눌렀을 때 선택 가능하도록 함
            Modifier.selectable(
                selected = isTextVisible,
                onClick = {
                    isTextVisible = true
                }
            ),
            verticalAlignment = Alignment.CenterVertically // Row 안에 들어가는 요소에 대한 배치(?)
        ) {
            RadioButton(
                selected = isTextVisible, // text 보이게 하기
                onClick = { isTextVisible = true }
            )
            Text(
                text = "Hello World Appears",
            )
        }
        Row(
            Modifier.selectable(
                selected = !isTextVisible,
                onClick = {
                    isTextVisible = false
                }
            )
        ) {
            RadioButton(
                selected = !isTextVisible, // text 안보이게 하기
                onClick = { isTextVisible = false }
            )
            Text(
                text = "Hello World Disappears",
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        Text(text = "Change the background color")
        Row(
            Modifier.selectable(
                selected = isBackgroundWhite,
                onClick = { isBackgroundWhite = true }
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(selected = isBackgroundWhite, onClick = { isBackgroundWhite = true })
            Text(
                text = "White",
            )
        }
        Row(
            Modifier.selectable(
                selected = !isBackgroundWhite,
                onClick = { isBackgroundWhite = false }
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(selected = !isBackgroundWhite, onClick = { isBackgroundWhite = false })
            Text(
                text = "Red",
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