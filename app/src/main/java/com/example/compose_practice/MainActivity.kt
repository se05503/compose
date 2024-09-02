package com.example.compose_practice

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_practice.ui.theme.ComposePracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // setContent 안에 모든 코드를 집어넣는 것은 좋지 않다.
            ComposePracticeTheme {
                ButtonExample(onButtonClicked = {
                    Toast.makeText(this@MainActivity, "Toast Message!", Toast.LENGTH_SHORT).show()
                })
            }
        }
    }
}

// 실제로 코드를 주로 작성하는 곳
@Composable
fun ButtonExample(onButtonClicked: () -> Unit) {
    Button(onClick = onButtonClicked) {
        Text(text = "Send")
    }
    // step1: Button 을 클릭했을 때 Toast 를 띄우기 -> Toast 메세지는 preview 에서는 확인할 수 없다.
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePracticeTheme {
        ButtonExample(onButtonClicked = {})
    }
}