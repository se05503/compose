package com.example.compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
                CatalogEx(catalogData)
            }
        }
    }

    companion object {
        val catalogData = CatalogData(
            imageUri = listOf("https://media.istockphoto.com/id/139717376/ko/%EC%82%AC%EC%A7%84/%EB%A1%A4%EB%9F%AC%EC%BD%94%EC%8A%A4%ED%84%B0.jpg?s=2048x2048&w=is&k=20&c=jaY_ApFfzncvTAoVmuC-uHZVC7oJKAhJcgTb2B8ir9M=","https://media.istockphoto.com/id/104144229/ko/%EC%82%AC%EC%A7%84/%EA%B7%B8%EB%9E%9C%EB%93%9C-%EC%BA%90%EB%85%84-%ED%92%8D%EA%B2%BD-at-dusk-%EC%8B%9C%EC%B2%AD%EC%88%9C-%EB%A9%94%ED%8A%B8%EB%A1%9C%ED%8F%B4%EB%A6%AC%EC%8A%A4-%EC%82%AC%EB%A7%89.jpg?s=612x612&w=0&k=20&c=RrNLszZZYSCih6NiQ7n6PtZ1JxeZsI1D4DAklrUHX7s="),
            imageDescription = "카탈로그 실습 구현용 이미지",
            title = "해변 놀이 공원",
            content = "놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다."
        )
    }
}

@Composable
fun CatalogEx(catalogData: CatalogData) {
    Column(modifier = Modifier.padding(20.dp)) {
        Card {
            Column(modifier = Modifier.padding(20.dp)) {
                AsyncImage(
                    model = catalogData.imageUri[0],
                    contentDescription = catalogData.imageDescription
                )
                Spacer(modifier = Modifier.size(12.dp))
                Text(text = catalogData.title, fontSize = 40.sp)
                Spacer(modifier = Modifier.size(12.dp))
                Text(text = catalogData.content)
            }
        }
        Spacer(modifier = Modifier.size(20.dp))
        Card {
            Column(modifier = Modifier.padding(20.dp)) {
                AsyncImage(
                    model = catalogData.imageUri[1],
                    contentDescription = catalogData.imageDescription,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

data class CatalogData(
    val imageUri: List<String>,
    val imageDescription: String,
    val title: String,
    val content: String
)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePracticeTheme {
        CatalogEx(MainActivity.catalogData)
    }
}