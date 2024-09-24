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
    // State Hoisting
    var pyeong by rememberSaveable { mutableStateOf("23") }
    var squaremeter by rememberSaveable { mutableStateOf((23 * 3.306).toString()) }
    PyeongToSquareMeterStateless(pyeong = pyeong, squareMeter = squaremeter) {
        // 후행 람다로 빼기
        if(it.isBlank()) {
            pyeong = ""
            squaremeter = ""
            return@PyeongToSquareMeterStateless
        } else {
            val numericValue = it.toFloatOrNull() ?: return@PyeongToSquareMeterStateless
            pyeong = numericValue.toString()
            squaremeter = (numericValue*3.306).toString()
        }
    }
}

@Composable
fun PyeongToSquareMeterStateless(
    pyeong: String,
    squareMeter: String,
    onPyeongChanged: (String) -> Unit // 평이 바뀌었을 때 호출할 콜백 함수
) {
    /*
    상태를 가지고 있지 않아서 상태를 바꾸지 않는다.
    onPyeongChanged 통해 상위 계층에서 상태를 바꾸도록 위임함
    해당 함수는 상태에 대해서 알지 못한다.
     */
    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = pyeong,
            onValueChange = onPyeongChanged,
            label = {
                Text("평")
            }
        )
        OutlinedTextField(
            value = squareMeter,
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