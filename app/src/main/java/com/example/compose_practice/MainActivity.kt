package com.example.compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    DialogEx()
                }
            }
        }
    }

}

@Composable
fun DialogEx() {
    var openDialog by remember { mutableStateOf(false) } // 초기값: false
    var counter by remember { mutableStateOf(0) } // 초기값: 0

    Column {
        Button(onClick = { openDialog = !openDialog }) { // openDialog = !openDialog 말고도 openDialog = true 도 같은 표현이다.
            Text("Open Dialog")
        }
        Text("카운터: $counter")
    }

    if(openDialog) {
        // openDialog = true 일 때만 다이얼로그가 뜬다.
        AlertDialog(onDismissRequest = {
            // 다이얼로그 바깥쪽을 클릭했을 떄의 이벤트 -> 다이얼로그를 안띄운다.
            openDialog = false
        },
        confirmButton = {
            // 오른쪽 버튼
            Button(onClick = {
                counter++ // 카운터 증가
                openDialog = false // 다이얼로그 종료
            }) {
                Text("Counter +1")
            }
        },
        dismissButton = {
            Button(onClick = {
                openDialog = false // 다이얼로그 종료
            }) {
                Text("Cancel")
            }
        },
        title = {
            // dialog's title
            Text(text = "Add Counter Dialog")
        },
        text = {
            // dialog's description
            Text("This is a sample dialog. \nWhen you click the confirm button(on the right), the counter increases by 1.")
        })
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePracticeTheme {
        DialogEx()
    }
}