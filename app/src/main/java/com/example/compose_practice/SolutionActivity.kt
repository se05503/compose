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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose_practice.ui.theme.ComposePracticeTheme

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
    // 근데 왜 이런 형식으로 하는걸까?
    private val _text = MutableLiveData("") // 이전) mutableStateOf("") | private 으로 바꿈으로써 외부에서 접근하지 못하도록 함. 같은 class 내에서는 접근 가능
    val text: LiveData<String> = _text // 외부에서는 _text 가 아닌 text 로 접근함

    val setText: (String) -> Unit = {
        _text.value = it
    }

    // toDoList 를 live data 로 변경한다.
    // Live data 는 value 가 바뀌어야만 state 가 바뀐다. list 의 일부만 수정해서는 update 가 되지 않는다. 따라서, list 전체를 복제해서 다시 전달해야한다.
    // 따라서, 좋은 방식은 아니다.
    private val _rawToDoList = mutableStateListOf<ToDoItem>() // mutableStateListOf -> 항목의 필드가 업데이트 되는 경우에는 업데이트 되지 않는다.
    private val _toDoList = MutableLiveData<List<ToDoItem>>(_rawToDoList)
    val toDoList: LiveData<List<ToDoItem>> = _toDoList // 밖에서는 해당 변수를 사용하면서 상태로 바꿔줘야 한다.


    val onSubmit: (String) -> Unit = {
        val key = (_rawToDoList.lastOrNull()?.key ?: 0) + 1
        _rawToDoList.add(ToDoItem(key, it))
        _toDoList.value = ArrayList(_rawToDoList)
        _text.value = ""
    }

    val onToggle: (key: Int, checked: Boolean) -> Unit = { key, checked ->
        // key 를 통해서 index 찾기
        // toDoList[i].isFinished = checked 가 아닌 copy 를 하는 이유?
        // 목적: UI refresh 를 위함 (항목의 값이 아닌 항목 자체를 바꿔야 ui 가 갱신된다)
        val i = _rawToDoList.indexOfFirst { it.key == key }
        _rawToDoList[i] = _rawToDoList[i].copy(isFinished = checked)
        _toDoList.value = _rawToDoList.toMutableList()
    }

    val onDelete: (key: Int) -> Unit = { key ->
        val i = _rawToDoList.indexOfFirst { it.key == key }
        _rawToDoList.removeAt(i) // 원소를 삭제하면 UI 가 갱신된다.
        _toDoList.value = mutableListOf<ToDoItem>().also {
            it.addAll(_rawToDoList)
        }
    }

    val onEdit: (key: Int, newText: String) -> Unit = { key, newText ->
        val i = _rawToDoList.indexOfFirst { it.key == key }
        _rawToDoList[i] = _rawToDoList[i].copy(text = newText)
        _toDoList.value = mutableListOf<ToDoItem>().also {
            it.addAll(_rawToDoList)
        }
    }
}

// 기본 값 = viewModel()
@Composable
fun TopLevel(viewModel: ToDoViewModel = viewModel()) {
    Scaffold {
        Column {
            // live data 가 update 될 때마다 갱신이 되려면 live data 를 state  로 바꿔야한다.
            // observeAsState 는 기본적으로 null 이 발생할 수 있어서, null 이 발생하지 않으려면 기본값을 설정해야 한다.
            ToDoInput(text = viewModel.text.observeAsState("").value, onTextChanged = viewModel.setText, onSubmit = viewModel.onSubmit)
            val toDoItems = viewModel.toDoList.observeAsState(emptyList()).value
            LazyColumn {
                // 어떤 항목이 재사용 가능한지 아닌지는 key 세팅을 통해 판단할 수 있다 (jetpack compose)
                // key 가 같으면 재사용 할 수 있다고 판단 -> 효과적인 렌더링 가능
                items(toDoItems, key = { it.key }) { todoItem ->
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