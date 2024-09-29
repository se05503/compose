package com.example.compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_practice.ui.theme.ComposePracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePracticeTheme {
                // Surface 로 감싸주는 것이 좋다.
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    AnimationTwoEx()
                }
            }
        }
    }
}

/*
step1 : 변수 생성
step2 : 변수를 적용
 */
@Composable
fun AnimationTwoEx() {
    var isNormalMode by remember { mutableStateOf(true) }
    // label : 문제가 발생했을 때 label 을 확인함. 도구에서 디버깅을 하기 위한 label.
    // 안붙여도 문법적으로 오류는 없지만 붙이는 것이 좋음
    val transition = updateTransition(targetState = isNormalMode, label = "Animation's Transition")
    // State<Color> -> Color (delegated property)
    val backgroundColor by transition.animateColor(label = "background color animation") { state ->
        when(state) {
            true -> Color.White
            false -> Color.Black
        }
    }
    val textColor by transition.animateColor(label = "text color animation") { state ->
        when(state) {
            true -> Color.Black
            false -> Color.White
        }
    }
    val alpha by transition.animateFloat(label = "alpha animation") { state ->
        when(state) {
            true -> 0.5f
            false -> 1f
        }
    }

    Column(
        modifier = Modifier
            .background(backgroundColor)
            .alpha(alpha)
    ) {
        RadioButtonWithText(
            text = "Normal Mode",
            selected = isNormalMode,
            color = textColor
        ) {
            isNormalMode = true
        }
        RadioButtonWithText(
            text = "Dark Mode",
            selected = !isNormalMode,
            color = textColor
        ) {
            isNormalMode = false
        }
        Crossfade(targetState = isNormalMode) { state ->
            when(state) {
                true -> {
                    Row {
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .background(Color.Red),
                        ) {
                            // slotApi
                            // 일반적으로 slotApi(lambda block) 를 제공하는 composable 함수는 마지막 parameter 가 content 인 경우가 많다.
                            Text(text = "1")
                        }
                        Box(modifier = Modifier
                            .size(20.dp)
                            .background(Color.Magenta)) {
                            Text(text = "2")
                        }
                        Box(modifier = Modifier
                            .size(20.dp)
                            .background(Color.Blue)) {
                            Text(text = "3")
                        }
                    }
                }
                false -> {
                    Column {
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .background(Color.Red),
                        ) {
                            // slotApi
                            // 일반적으로 slotApi(lambda block) 를 제공하는 composable 함수는 마지막 parameter 가 content 인 경우가 많다.
                            Text(text = "A")
                        }
                        Box(modifier = Modifier
                            .size(20.dp)
                            .background(Color.Magenta)) {
                            Text(text = "B")
                        }
                        Box(modifier = Modifier
                            .size(20.dp)
                            .background(Color.Blue)) {
                            Text(text = "C")
                        }
                        Surface(modifier = Modifier.background(Color.White)) {
                            Text(text = "Text 를 Surface 로 감싸주기")
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
        AnimationTwoEx()
    }
}

@Composable
fun RadioButtonWithText(
    text: String,
    color: Color = Color.Black, // color 값을 설정하지 않았을 때의 기본값
    selected: Boolean,
    onClick: () -> Unit
    // content 는 받지 않는다.
) {
    // Row 에 selectable 을 설정했다고 RadioButton 에 속성을 설정하지 않으면 안된다. (작동하지 않는다.)
    // 즉, RadioButton 은 필수적으로 세팅해야하고, Row 는 클릭 설정을 세팅해도 되고 안해도 된다.
    Row(
        modifier = Modifier.selectable(
            selected = selected,
            onClick = onClick
        ),
        verticalAlignment = Alignment.CenterVertically // row 안에 들어가는 요소에 대한 레이아웃 배치
    ) {
        RadioButton(selected = selected, onClick = onClick)
        Text(
            text = text,
            color = color
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RadioButtonWithTextPreview() {
    RadioButtonWithText(
        text = "Radio Button",
        color = Color.Red,
        selected = true,
        onClick = { }
    )
}