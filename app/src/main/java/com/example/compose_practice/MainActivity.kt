package com.example.compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_practice.ui.theme.ComposePracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePracticeTheme {
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    ToDoListEx()
                }
            }
        }
    }
}

val todoList = ArrayList<String>()

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePracticeTheme {
        ToDoListEx()
    }
}

var isInputButtonClicked by mutableStateOf(false)

@Composable
fun ToDoListEx() {
    Column {
        val todoThing = ToDoInputItem()
        if(isInputButtonClicked) { // UI 가 생겼다가 다시 사라지는 부분 코드 (추정)
            registerToDoList(todoThing)
            isInputButtonClicked = false // recomposition 을 발생시키기 위해 값을 다시 바꿈
        } else {
            LazyColumn {
                items(todoList) { todo ->
                    ToDoItem(todo)
                }
            }
        }
    }
}

@Composable
fun registerToDoList(todoThing: String) {
    todoList.add(todoThing)
    LazyColumn {
        items(todoList) { todo ->
            ToDoItem(todo)
        }
    }
}

@Composable
fun ToDoInputItem(): String {
    // 입력하기 전 단일 List UI
    var todoContent by remember { mutableStateOf("") }
    Card(
        elevation = 5.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ) {
            OutlinedTextField(
                value = todoContent,
                onValueChange = {
                    todoContent = it },
                label = {
                    Text(text = "What's your plan?")
                }
            )
            Spacer(modifier = Modifier.size(8.dp))
            Button(onClick = {
                isInputButtonClicked = true
            }) {
                Text(
                    text = "입력"
                )
            }
        }
    }
    return todoContent
}

@Preview(showBackground = true)
@Composable
fun ToDoInputPreview() {
    ToDoInputItem()
}

@Composable
fun ToDoItem(todo: String) {
    // 입력한 후 단일 List UI
    var isFinished by remember { mutableStateOf(false) }
    Card(
        elevation = 5.dp,
        modifier = Modifier.padding(5.dp) // padding 을 넣어줘야 elevation 효과를 확인할 수 있다.
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = todo,
                modifier = Modifier.weight(1f)
            )
            Text(text = "완료")
            Checkbox(checked = isFinished, onCheckedChange = {
                isFinished = !isFinished
            })
            Button(onClick = { /*TODO*/ }) {
                Text("수정")
            }
            Spacer(modifier = Modifier.size(8.dp))
            Button(onClick = { /*TODO*/ }) {
                Text("삭제")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoItemPreview() {
    ToDoItem("점심 약속")
}


