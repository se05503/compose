package com.example.compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
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
        var isChecked = remember { mutableStateOf(false) }
        Checkbox(checked = isChecked.value, onCheckedChange = {
            isChecked.value = !isChecked.value
        })
        Text(text = "CheckBox")
    }

    // 구조를 분해한다 = 비구조화, 반구조화 (destruction)
    val (a,b) = listOf(2,3)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePracticeTheme {
        CheckBoxEx()
    }
}