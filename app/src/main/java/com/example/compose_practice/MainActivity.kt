package com.example.compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
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

@Composable
fun AnimationTwoEx() {
    var isNormalMode by remember { mutableStateOf(true) }
    Column {
        Row(
            modifier = Modifier.selectable(
                selected = isNormalMode, onClick = { isNormalMode = true }
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(selected = isNormalMode, onClick = { isNormalMode = true })
            Text(text = "Normal Mode")
        }
        Row(
            modifier = Modifier.selectable(
                selected = !isNormalMode, onClick = { isNormalMode = false }
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(selected = !isNormalMode, onClick = { isNormalMode = false })
            Text(text = "Dark Mode")
        }
        Row {
            Box(
                modifier = Modifier.size(20.dp).background(Color.Red),
            ) {
                Text(text = "1")
            }
            Box(modifier = Modifier.size(20.dp).background(Color.Magenta)) {
                Text(text = "2")
            }
            Box(modifier = Modifier.size(20.dp).background(Color.Blue)) {
                Text(text = "3")
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

@Preview(showBackground = true)
@Composable
fun RadioButtonWithTextPreview() {
    var isRadioButtonChecked by remember { mutableStateOf(true) }
    Row(
        modifier = Modifier.selectable(
            selected = isRadioButtonChecked,
            onClick = { isRadioButtonChecked = true }
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = isRadioButtonChecked, onClick = { isRadioButtonChecked = true })
        Text(
            text = "Radio Button",
            color = Color.Red
        )
    }
}