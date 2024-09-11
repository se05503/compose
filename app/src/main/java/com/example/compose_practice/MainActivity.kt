package com.example.compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.compose_practice.ui.theme.ComposePracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePracticeTheme {
                PracticeChap12()
            }
        }
    }
}

@Composable
fun PracticeChap12() {
    // Ch03-12. 프로필 카드 구현 실습 코드 (아직 미완성)
    androidx.compose.material.Surface(
        elevation = 5.dp,
        modifier = Modifier.padding(10.dp).height(100.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = "https://cdn.pixabay.com/photo/2015/04/23/22/00/flowers-736885_960_720.jpg",
                contentDescription = "example network image",
                modifier = Modifier
                    .weight(2f)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                // placeholder 값으로 무엇을 지정해야하지?
                placeholder = painterResource(R.drawable.ic_android)
            )
            Spacer(modifier = Modifier.size(12.dp))
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
        PracticeChap12()
    }
}