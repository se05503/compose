package com.example.compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
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
                    SnackBarEx()
                }
            }
        }
    }

}

@Composable
fun SnackBarEx() {

    // step3
    // @Composable layer 에서 쓰기 때문에 rememberCoroutineScope 를 만들어서 쓰는 것이 좋다.
    // coroutineScope 를 만드는 이유는 Snackbar 가 suspend fun 이기 때문이다.
    // suspend fun 은 coroutine 범위 내에서만 호출할 수 있는 함수이다.
    val coroutineScope = rememberCoroutineScope()

    // step1
    // rememberScaffoldState() 를 사용하여 scaffoldState 를 만듭니다.
    // 만든 scaffoldState 를 가지고 Scaffold 의 인자에 설정합니다.
    var counter by remember { mutableStateOf(0) }
    val scaffoldState = rememberScaffoldState()

    /*
    - rememberScaffoldState 에 snackbarHostState 를 지정하는 경우
    val snackbarHostState = remember {SnackbarHostState()}
    val scaffoldState = rememberScaffoldState(snackbarHostState = snackbarHostState)
     */
    Scaffold(scaffoldState = scaffoldState){

        /*
        // composable 안에서는 coroutineScope 를 호출하려면 LaunchedEffect 를 감싸줘야 한다.
        LaunchedEffect(scaffoldState.snackbarHostState) {
            // key = scaffoldState.snackbarHostState
            // key 값이 바뀌기 전에는 다시 호출되지 않는다.
            // LaunchedEffect 는 처음 뜰 때만 호출하고, 상태가 바뀌기 전에는 호출되지 않는다.
            coroutineScope.launch {
                // Snackbar 는 suspend fun 이기 때문에 coroutine 범위 내에서만 호출할 수 있다.
                // snackbarHostState 를 통해서 snackbar를 만들 수 있다.
                scaffoldState.snackbarHostState.showSnackbar(
                    message = "Counter: $counter",
                    actionLabel = "Cancel",
                    duration = SnackbarDuration.Short
                )
            }
        }
        */

        Button(onClick = {
            counter++
            // onClick 은 composable 안이 아니기 때문에 괜찮다.
            coroutineScope.launch {
                // Snackbar 는 suspend fun 이기 때문에 coroutine 범위 내에서만 호출할 수 있다.
                // snackbarHostState 를 통해서 snackbar를 만들 수 있다.
                scaffoldState.snackbarHostState.showSnackbar(
                    message = "Counter: $counter",
                    actionLabel = "Cancel",
                    duration = SnackbarDuration.Short
                )

                /*
                - 결과값 변수를 설정하고 응답값에 따라 처리를 다르게 하고 싶은 경우
                val result = scaffoldState.snackbarHostState.showSnackbar(
                    message = "Counter: $counter",
                    actionLabel = "Cancel",
                    duration = SnackbarDuration.Short
                )
                when(result) {
                    SnackbarResult.ActionPerformed -> {}
                    SnackbarResult.Dismissed -> {}
                }
                 */
            }
        }) {
            Text("Add")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePracticeTheme {
        SnackBarEx()
    }
}