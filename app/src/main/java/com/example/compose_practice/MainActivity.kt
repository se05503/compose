package com.example.compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_practice.ui.theme.ComposePracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePracticeTheme {
                // Surface 로 감싸주는 것이 좋다
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PyeongToSquareMeter()
                }
            }
        }
    }
}

@Composable
fun PyeongToSquareMeter() {
    var pyeong by rememberSaveable { mutableStateOf("23") }
    var squaremeter by rememberSaveable { mutableStateOf((23 * 3.306).toString()) }

    Column(modifier = Modifier.padding(16.dp)) {
        /*
        jetpack compose 는 상태가 바뀌지 않으면 업데이트가 되지 않는다.
        따라서 값이 수정될려면 상태가 바뀌어야한다.
        configuration 이 바뀌는 대표적인 경우 = rotation (회전)
        저장공간의 한계로 모든 값을 rememberSaveable 로 하는 것은 좋지 않다. (변수가 2개 정도면 괜찮음)
        remember 와 rememberSaveable 만 근본적인 method 이고 나머지는 utility 이다.
         */
        OutlinedTextField(
            value = pyeong,
            onValueChange = {
                if(it.isBlank()) {
                    pyeong = ""
                    squaremeter = ""
                    return@OutlinedTextField // 이게 무슨 의미지?
                } else {
                    // float(실수)으로 바꿀 수 없는 것은 null 로 return 하겠다. null 이면 강제종료하겠다.
                    val numericValue = it.toFloatOrNull() ?: return@OutlinedTextField
                    pyeong = numericValue.toString()
                    squaremeter = (numericValue*3.306).toString()
                }
            },
            label = {
                Text("평")
            }
        )
        OutlinedTextField(
            value = squaremeter,
            onValueChange = {},
            label = {
                Text("제곱미터")
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePracticeTheme {
        PyeongToSquareMeter()
    }
}