package com.example.compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.compose_practice.ui.theme.ComposePracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePracticeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CanvasEx()
                }
            }
        }
    }

}

@Composable
fun CanvasEx() {
    /*
    Canvas 는 무언가 그릴 수 있는 공간
    Modifier 를 반드시 설정해야 한다 (단점)
    핸드폰에서는 매우 작게 뜨고, Preview 에서만 뜬다.
    y 값이 커질수록 아래로 가고, y 값이 작아질수록 위로 간다.
    */
    Canvas(modifier = Modifier.size(20.dp)) {
        // 시작(Offset)과 끝(Offset)의 x,y 좌표 지정
        drawLine(Color.Red, Offset(30f, 10f), Offset(50f,10f))

        // 매개변수: 색상, 반지름, 중앙 위치(Offset)
        drawCircle(Color.Yellow, 10f, Offset(15f, 30f))

        // Offset(위치)
        drawRect(Color.Green, Offset(30f,40f), Size(10f, 10f))

        /*
         drawLine 을 활용
         moveTo(2.01f, 21.0f) : 처음 위치
         lineTo(23.0f, 12.0f) : 이쪽으로 이동
         lineTo(2.01f, 3.0f)
         lineTo(2.0f, 10.0f)
         lineToRelative(15.0f, 2.0f) : 해당 거리만큼 이동
         lineToRelative(-15.0f, 2.0f)
         close() -> 처음 위치 이동
         */
        drawLine(Color.Blue, Offset(2.01f, 21.0f), Offset(23.0f, 12.0f))
        drawLine(Color.Blue, Offset(23.0f, 12.0f), Offset(2.01f, 3.0f))
        drawLine(Color.Blue, Offset(2.01f, 3.0f), Offset(2.0f, 10.0f))
        drawLine(Color.Blue, Offset(2.0f, 10.0f), Offset(17.0f, 12.0f))
        drawLine(Color.Blue, Offset(17.0f, 12.0f), Offset(2.0f, 14.0f))
        drawLine(Color.Blue, Offset(2.0f, 14.0f), Offset(2.01f, 21.0f))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePracticeTheme {
        CanvasEx()
    }
}