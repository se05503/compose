package com.example.compose_practice

import android.os.Bundle
import android.util.Log
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePracticeTheme {
        ToDoListEx()
    }
}

val todoList = ArrayList<String>()
var isInputButtonClicked by mutableStateOf(false)
var todoContent by mutableStateOf("")

@Composable
fun ToDoListEx() {
    Column {
        val todoThing = ToDoInputItem()
        if(isInputButtonClicked) {
            // 할 일 등록
            if(todoThing == "") {
                val scaffoldState = rememberScaffoldState()
                LaunchedEffect(scaffoldState.snackbarHostState) {
                    scaffoldState.snackbarHostState.showSnackbar("내용을 입력해주세요!")
                }
                Scaffold(
                    scaffoldState = scaffoldState
                ) {
                    // 얘 넣으니까 "코드를 저렇게 넣었는데 왜 안깜빡이지?" 에 대한 의문점이 풀림 (다시 예상한대로 동작)
                    // 근데 얘를 왜 넣는거?
                }
                isInputButtonClicked = false
            } else {
                registerToDoList(todoThing) // 아쉬운 점: 버튼을 누를 때마다 새로 추가된 리스트 뿐만이 아니라 처음 할 일 리스트부터 다시 ui 를 그림
                isInputButtonClicked = false // recomposition 을 발생시키기 위해 값을 다시 바꿈
                todoContent = ""
            }
        } else {
            // 동작은 하지만 매우 마음에 들지 않는 코드, 하지만 이렇게 하지 않으면 UI 가 사라진다.
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


