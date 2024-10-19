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


@Composable
fun CompositionLocalEx() {
    // CompositionLocalProvider 를 이용하면 람다 내에 암시적 값을 설정할 수 있다.
    // LocalContentAlpha 를 ContentAlpha.disabled 로 설정하겠다.
    Card(
        modifier = Modifier.padding(16.dp)
    ) {
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.disabled) {
            // disabled, medium, high 로 갈수록 색이 진해진다.
            // 중첩적인 사용이 가능하다.
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




