package com.example.compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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

@Composable
fun CheckBoxEx() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        // checked = getter, setChecked = setter
        var (checked, setChecked) = remember { mutableStateOf(false) }
        Checkbox(checked = checked, onCheckedChange = {
            checked = !checked
            // setChecked(!checked)
        })
        Text(text = "CheckBox")
    }

    // 구조를 분해한다 = 비구조화, 반구조화 (destruction)
    val (a, b) = listOf(2, 3)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePracticeTheme {
        CheckBoxEx()
    }
}