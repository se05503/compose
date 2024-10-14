package com.example.compose_practice.ui.theme

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class SolutionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePracticeTheme {
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    TopLevel()
                }
            }
        }
    }
}

class ToDoViewModel : ViewModel() {
    // viewmodel 의 생명주기와 composable 의 생명주기는 서로 다르다. -> remember 를 사용하지 못한다.
    // (이전) 함수 내의 지역 변수 → (이후) 클래스 내에서는 프로퍼티로 작용
    // 프로퍼티로는 destruction 이 불가능하다.
    // by 를 통한 상태 위임(delegate)이 가능하다.
    val text = mutableStateOf("")

    // 해당 리스트에 항목이 추가, 삭제, 변경(값이 변경되는 것이 아닌 항목 자체가 변경되는 경우) 될 경우
    // ui 가 refresh 된다. 이외의 경우(ex. 항목의 값이 바뀌는 경우)에는 refresh 되지 않는다.
    val toDoList = mutableStateListOf<ToDoItem>()

    val onSubmit: (String) -> Unit = {
        // 리스트에 데이터 넣기
        val key = (toDoList.lastOrNull()?.key ?: 0) + 1
        toDoList.add(ToDoItem(key, it))
        text.value = ""
    }

    val onToggle: (key: Int, checked: Boolean) -> Unit = { key, checked ->
        // key 를 통해서 index 찾기
        // toDoList[i].isFinished = checked 가 아닌 copy 를 하는 이유?
        // 목적: UI refresh 를 위함 (항목의 값이 아닌 항목 자체를 바꿔야 ui 가 갱신된다)
        val i = toDoList.indexOfFirst { it.key == key }
        toDoList[i] = toDoList[i].copy(isFinished = checked)
    }

    val onDelete: (key: Int) -> Unit = { key ->
        val i = toDoList.indexOfFirst { it.key == key }
        toDoList.removeAt(i) // 원소를 삭제하면 UI 가 갱신된다.
    }

    val onEdit: (key: Int, newText: String) -> Unit = { key, newText ->
        val i = toDoList.indexOfFirst { it.key == key }
        toDoList[i] = toDoList[i].copy(text = newText)
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePracticeTheme {
        TopLevel()
    }
}

// 기본 값 = viewModel()
@Composable
fun TopLevel(viewModel: ToDoViewModel = viewModel()) {

    Scaffold {
        Column {
            ToDoInput(text = viewModel.text.value, onTextChanged = { viewModel.text.value = it }, onSubmit = viewModel.onSubmit)
            LazyColumn {
                // 어떤 항목이 재사용 가능한지 아닌지는 key 세팅을 통해 판단할 수 있다 (jetpack compose)
                // key 가 같으면 재사용 할 수 있다고 판단 -> 효과적인 렌더링 가능
                items(viewModel.toDoList, key = { it.key }) { todoItem ->
                    ToDoOutput(
                        toDoItem = todoItem,
                        onToggle = viewModel.onToggle,
                        onDelete = viewModel.onDelete,
                        onEdit = viewModel.onEdit
                    )
                }
            }
        }
    }
}

@Composable
fun ToDoInput(
    text: String,
    onTextChanged: (String) -> Unit,
    onSubmit: (String) -> Unit
) {
    // 내가 만든 ToDoInputItem 함수에서 가져옴
    Card(
        elevation = 5.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = onTextChanged,
                label = {
                    Text(text = "What's your plan?")
                }
            )
            Spacer(modifier = Modifier.size(8.dp))
            Button(onClick = {
                onSubmit(text)
            }) {
                Text(
                    text = "입력"
                )
            }
        }
    }
}

@Composable
fun ToDoOutput(
    toDoItem: ToDoItem,
    onToggle: (key: Int, checked: Boolean) -> Unit,
    onDelete: (key: Int) -> Unit,
    onEdit: (key: Int, newText: String) -> Unit
) {
    var isEditing by remember { mutableStateOf(false) }
    Crossfade(targetState = isEditing) { status ->
        when (status) {
            true -> {
                val (newText, setNewText) = remember { mutableStateOf(toDoItem.text) }
                Card(
                    elevation = 5.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(10.dp)
                    ) {
                        OutlinedTextField(
                            value = newText,
                            onValueChange = setNewText,
                            label = {
                                Text(text = "What's your plan?")
                            }
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Button(onClick = {
                            onEdit(toDoItem.key, newText)
                            isEditing = false
                        }) {
                            Text(
                                text = "완료"
                            )
                        }
                    }
                }
            }
            false -> {
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
                            text = toDoItem.text,
                            modifier = Modifier.weight(1f)
                        )
                        Text(text = "완료")
                        Checkbox(checked = toDoItem.isFinished, onCheckedChange = { checked ->
                            onToggle(toDoItem.key, checked)
                        })
                        Button(onClick = { isEditing = true }) {
                            Text("수정")
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        Button(onClick = {
                            onDelete(toDoItem.key)
                        }) {
                            Text("삭제")
                        }
                    }
                }
            }
        }
    }
}

data class ToDoItem(
    val key: Int,
    val text: String,
    val isFinished: Boolean = false
)