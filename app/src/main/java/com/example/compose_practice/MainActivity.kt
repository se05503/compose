package com.example.compose_practice

import android.os.Bundle
import android.util.Log
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
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.example.compose_practice.ui.theme.ComposePracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePracticeTheme {
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    EffectEx()
                }
            }
        }
    }
}

@Composable
fun EffectEx(lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current) {
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(scaffoldState.snackbarHostState) {
        scaffoldState.snackbarHostState.showSnackbar("Launched Effect practicing")
    }
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            // SAM(Single Abstract Method) 방식
            // 단일 abstract function 을 가진 interface(LifecycleEventObserver)는
            // 람다 형태로 추상 함수의 매개변수를 받아올 수 있다.
            when(event) {
                Lifecycle.Event.ON_CREATE -> Log.d("DisposableEffect", "ON_CREATE")
                Lifecycle.Event.ON_START -> Log.d("DisposableEffect", "ON_START")
                Lifecycle.Event.ON_STOP -> Log.d("DisposableEffect", "ON_STOP")
                Lifecycle.Event.ON_PAUSE -> Log.d("DisposableEffect", "ON_PAUSE")
                Lifecycle.Event.ON_RESUME -> Log.d("DisposableEffect", "ON_RESUME")
                Lifecycle.Event.ON_DESTROY -> Log.d("DisposableEffect", "ON_DESTROY")
                else -> Log.d("DisposableEffect", "ELSE")
            }
        }
        // 할당
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            // 해제
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    Scaffold(
        scaffoldState = scaffoldState
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePracticeTheme {
        EffectEx()
    }
}

