package com.example.compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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

/*

 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // setContent 안에 모든 코드를 집어넣는 것은 좋지 않다.
            ComposePracticeTheme {
                Text("Hello")
//                Greeting("Android")
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    // color parameter
//    Text(color = Color.Green, text = "Hello $name")

    // 해쉬값으로 색상을 전달하자. (ARGB 순)
    // 16진수 : 0x , 알파값을 넣어야함
//    Text(color = Color(0xff00ffff), text = "Hello $name")

    // fontSize
    // 키워드 파라미터를 사용하기 때문에 순서는 상관없다.
//    Text(color = Color(0xff00ffff), text = "Hello $name", fontSize = 30.sp)

    // fontWeight
//    Text(
//        color = Color(0xff00ffff),
//        text = "Hello $name",
//        fontSize = 30.sp,
//        fontWeight = FontWeight.Bold
//    )

    // fontFamily (글꼴)
//    Text(
//        color = Color(0xff00ffff),
//        text = "Hello $name",
//        fontSize = 30.sp,
//        fontWeight = FontWeight.Bold,
//        fontFamily = FontFamily.Cursive
//    )

    // letterSpacing
    // text 에서는 dp 단위를 거의 쓰지 않는다. sp 단위를 써야한다.
//    Text(
//        color = Color(0xff00ffff),
//        text = "Hello $name",
//        fontSize = 30.sp,
//        fontWeight = FontWeight.Bold,
//        fontFamily = FontFamily.Cursive,
//        letterSpacing = 2.sp
//    )

    // maxLine
//    Text(
//        color = Color(0xff00ffff),
//        text = "Hello $name\nHello $name\nHello $name",
//        fontSize = 30.sp,
//        fontWeight = FontWeight.Bold,
//        fontFamily = FontFamily.Cursive,
//        letterSpacing = 2.sp,
//        maxLines = 2
//    )

    // textDecoration
//    Text(
//        color = Color(0xff00ffff),
//        text = "Hello $name\nHello $name\nHello $name",
//        fontSize = 30.sp,
//        fontWeight = FontWeight.Bold,
//        fontFamily = FontFamily.Cursive,
//        letterSpacing = 2.sp,
//        maxLines = 2,
//        textDecoration = TextDecoration.Underline
//    )

    // textAlign
    // modifer 에서 width 는 가로만 바꾸는 것, size 는 가로 세로 둘 다 바꾸는 것이다.
    Text(
        modifier = Modifier.size(300.dp),
        color = Color(0xff00ffff),
        text = "Hello $name\nHello $name\nHello $name",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Cursive,
        letterSpacing = 2.sp,
        maxLines = 2,
        textDecoration = TextDecoration.Underline,
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePracticeTheme {
        Greeting("Android")
    }
}