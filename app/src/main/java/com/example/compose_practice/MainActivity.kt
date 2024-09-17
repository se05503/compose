package com.example.compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
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
            imageUri = "https://media.istockphoto.com/id/139717376/ko/%EC%82%AC%EC%A7%84/%EB%A1%A4%EB%9F%AC%EC%BD%94%EC%8A%A4%ED%84%B0.jpg?s=2048x2048&w=is&k=20&c=jaY_ApFfzncvTAoVmuC-uHZVC7oJKAhJcgTb2B8ir9M=",
            imageDescription = "카탈로그 실습 구현용 이미지",
            title = "해변 놀이 공원",
            content = "놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다."
        )
    }
}

@Composable
fun CatalogEx(catalogData: CatalogData) {
    Card(modifier = Modifier.padding(20.dp)) {
            Column(modifier = Modifier.padding(20.dp)) {
            AsyncImage(model = catalogData.imageUri, contentDescription =catalogData.imageDescription)
            Spacer(modifier = Modifier.size(12.dp))
            Text(text =catalogData.title, fontSize = 40.sp)
            Spacer(modifier = Modifier.size(12.dp))
            Text(text =catalogData.content)
        }
    }
}

data class CatalogData(
    val imageUri: String,
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