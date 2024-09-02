package com.example.compose_practice

import android.os.Bundle
import android.widget.Space
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
        // contentDescription 의 경우, text 와 같은 의미를 가진다면 쓰지 않아도 된다. (null)
        // 만약 text 와 다른 의미를 가진다면 해당 내용을 쓰는게 좋다.
        Icon(
            imageVector = Icons.Filled.Send,
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        Text(text = "Send")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePracticeTheme {
        ButtonExample(onButtonClicked = {})
    }
}