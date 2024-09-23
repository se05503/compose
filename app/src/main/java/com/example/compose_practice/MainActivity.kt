package com.example.compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_practice.ui.theme.ComposePracticeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePracticeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BottomAppBarEx()
                }
            }
        }
    }

}

@Composable
fun BottomAppBarEx() {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    var counter by remember {
        mutableStateOf(0)
    }

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomAppBar {
                Text("BottomBar")
                Spacer(modifier = Modifier.size(4.dp))
                Button(onClick = {
                    // composable 내부가 아니기 때문에 바로 coroutineScope.launch 를 쓸 수 있다. (LaunchedEffect 를 쓰지 않아도 됨)
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            "SnackBar!"
                        )
                    }
                }) {
                    Text("Button")
                }
                Spacer(modifier = Modifier.size(4.dp))
                Button(onClick = {
                    counter++
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            "counter: $counter"
                        )
                    }
                }) {
                    Text("Increase")
                }
                Spacer(modifier = Modifier.size(4.dp))
                Button(onClick = {
                    counter--
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            "counter: $counter"
                        )
                    }
                }) {
                    Text("Decrease")
                }
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "counter: $counter",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePracticeTheme {
        BottomAppBarEx()
    }
}