package com.example.compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_practice.ui.theme.ComposePracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        codeCacheDir.setReadOnly()
        setContent {
            ComposePracticeTheme {
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    ThemeEx()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePracticeTheme {
        ThemeEx()
    }
}

@Composable
fun ThemeEx() {
    Card(
        modifier = Modifier.padding(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Theme 공부중")
            Text("Theme 공부중")
            Text("Theme 공부중")
            Text("Theme 공부중")
            Text("Theme 공부중")
            Text("Theme 공부중")
            Button(onClick = { /*TODO*/ }) {
                Text("버튼")
            }
        }
    }
}





