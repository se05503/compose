package com.example.compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.example.compose_practice.ui.theme.ComposePracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePracticeTheme {
                Surface( // Surface 의 역할이 정확히 뭔지 잘 모르겠음
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CustomDialogEx()
                }
            }
        }
    }

}

@Composable
fun CustomDialogEx() {
    var openDialog by remember { mutableStateOf(false) } // 초기값: false
    var counter by remember { mutableStateOf(0) } // 초기값: 0

    Column {
        Button(onClick = { openDialog = true }) {
            Text("Open CustomDialog")
        }
        Text("counter: $counter")
    }

    if(openDialog) {

        Dialog(onDismissRequest = {
            // 다이얼로그 바깥쪽을 클릭했을 떄의 이벤트 -> 다이얼로그를 dismiss 한다.
            openDialog = false
        }) {
            // content -> 다이얼로그의 모양(형태) 및 기능을 작성하는 곳
            Surface() {
                // 컨텐츠가 있는 영역들은 Surface 로 맨바깥쪽에 감싸주는 것이 좋다.
                // 배경 색상을 제공해서 하얀색 배경이 만들어짐
                Column(modifier = Modifier.padding(8.dp)) {
                    Text("Click the button.\n* Clicking +1 increases the value.\n* Clicking -1, decreases the value.")
                    Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        Button(onClick = {
                            openDialog = false
                        }) {
                            Text(text = "Cancel")
                        }
                        Spacer(modifier = Modifier.size(4.dp))
                        Button(onClick = { counter-- }) {
                            Text(text = "-1")
                        }
                        Spacer(modifier = Modifier.size(4.dp))
                        Button(onClick = { counter++ }) {
                            Text(text = "+1")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePracticeTheme {
        CustomDialogEx()
    }
}