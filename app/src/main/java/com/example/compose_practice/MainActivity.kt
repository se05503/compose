package com.example.compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose_practice.ui.theme.ComposePracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePracticeTheme {
                CheckBoxEx()
            }
        }
    }
}

/*
legacy view : 코드를 넣지 않아도 checkbox 가 동작함
jetpack compose 에서는 상태 변화를 시키지 않으면 동작하지 않음
recomposition 은 언제 일어날 지 모른다.
jetpack compose 의 status = mutableStateOf(초기값) -> remember 을 사용해야 함
remember 을 사용하지 않으면 Composable 함수에서 recomposition이 발생할 때 상태가 날라간다.
remember : status 를 기억했다가 나중에 다시 쓰겠다.

interface MutableState<T> : State<T> {
    override var value: T
    operator fun component1(): T
    operator fun component2(): (T) -> Unit
}

(2,3) 을 a와 b로 분리하는 것을 destruction (비구조화, 반구조화, 구조분해) 라고 한다. 뭉치는 것은 구조화.
T 타입 = Getter
(T) -> Unit : T 타입으로 받고 아무것도 반환하지 않는 것 = Setter
 - 값이 바뀌었을때 rendering(recompositino)이 됨
 delegate property(by) -> 프로퍼티를 붙이지 않아도 됨

방법 1
var (checked, setChecked) = remember { mutableStateOf(false) }
        Checkbox(checked = checked, onCheckedChange = {
            setChecked(!checked)
        })
        Text(text = "CheckBox")

방법2
setChecked(it) -> Checkbox가 제공하는 it 값을 그대로 받아서 상태를 업데이트하는 것

방법3
체크박스가 바뀔 때 setter 값이 먼저 호출되고 getter 값이 갱신된다.

텍스트 클릭
 체크박스 같은 경우는 setter 값이 체크박스 상태에 따라 자동으로 변경된다. 그리고 그 값으로 getter 값이 갱신된다.
 텍스트 같은 경우 자동으로 상태를 변경하는 기능이 없어서, 클릭할 때마다 수동으로 변경시켜야한다.
 */

@Composable
fun CheckBoxEx() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        val (getter, setter) = remember { mutableStateOf(false) }
        Checkbox(
            checked = getter,
            onCheckedChange = setter
        )
        Text(
            text = "CheckBox",
            modifier = Modifier.clickable {
                setter(!getter)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePracticeTheme {
        CheckBoxEx()
    }
}