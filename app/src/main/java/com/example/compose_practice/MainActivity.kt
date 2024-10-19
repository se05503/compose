package com.example.compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_practice.ui.theme.ComposePracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        codeCacheDir.setReadOnly()
        setContent {
            ComposePracticeTheme {
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CompositionLocalEx()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePracticeTheme {
        CompositionLocalEx()
    }
}

// CompositionLocalProvider 에서 사용할 수 있다.
val LocalElevation = compositionLocalOf { 8.dp }

@Composable
fun CompositionLocalEx() {

    // 기본값은 8.dp 이지만 12.dp 로 override 함
    CompositionLocalProvider(LocalElevation provides 12.dp) {
        Card(
            modifier = Modifier.padding(16.dp),
            elevation = LocalElevation.current
        ) {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.disabled) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    test()
                    Text("CompositionLocalProvider 실습중")
                    Text("${LocalContentAlpha.current}")
                }
            }
        }
    }
}

@Composable
private fun test() {
    Text("CompositionLocalProvider 실습중")
    Text("CompositionLocalProvider 실습중")
    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        CompositionLocalProvider(LocalContentColor provides Color.Blue) {
            Text("CompositionLocalProvider 실습중")
            Text("CompositionLocalProvider 실습중")
            Text("${LocalContentColor.current}") // Color(R,G,B,alpha, ...)
            Text("${LocalContentAlpha.current}")
        }
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
            Text("CompositionLocalProvider 실습중")
        }
        Text("CompositionLocalProvider 실습중")
    }
}




