package com.example.compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.compose_practice.ui.theme.ComposePracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePracticeTheme {
                Scaffold()
            }
        }
    }
}

@Composable
fun CheckBoxWithContent(
    checked: Boolean,
    toggleState: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { toggleState() }
    ) {
        Checkbox(checked = checked, onCheckedChange = { toggleState() })
        content()
    }
}

@Composable
fun Scaffold() {
    var checked by remember { mutableStateOf(false) }
    androidx.compose.material.Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Scaffold") },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(imageVector = Icons.Filled.ArrowBack, contentDescription = "뒤로가기")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {

            }
        }
    ) {
        androidx.compose.material.Surface(modifier = Modifier.padding(8.dp)) {
            CheckBoxWithContent(checked = checked, toggleState = { checked = !checked }) {
                Text(text = "Compose Scaffold")
            }
        }
    }
}


@Composable
fun PracticeChap12() {
    // Ch03-12. 프로필 카드 구현 실습 코드 (아직 미완성)
    androidx.compose.material.Surface(
        elevation = 5.dp,
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(
            modifier = Modifier.height(80.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = "https://cdn.pixabay.com/photo/2015/04/23/22/00/flowers-736885_960_720.jpg",
                contentDescription = "example network image",
                modifier = Modifier.weight(2f)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Column(modifier = Modifier.weight(8f)) {
                Text(text = "Dalinaum")
                Text(
                    text = "엔텔로프 캐년은 죽기 전에 꼭 봐야할 절경으로 소개되었습니다."
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePracticeTheme {
        Scaffold()
    }
}