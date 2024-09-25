package com.example.compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.compose_practice.ui.theme.ComposePracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePracticeTheme {
                // Surface 로 감싸주는 것이 좋다.
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ConstraintLayoutEx(cardItem)
                }
            }
        }
    }

    companion object {
        val cardItem = CardItem(
            R.drawable.a2,
            "엔텔로프 캐년",
            "Dalinaum",
            "엔텔로프 캐년은 죽기 전에 꼭 봐야할 정경으로 소개되었습니다."
        )
    }
}

data class CardItem(
    val image: Int,
    val imageDescription: String,
    val author: String,
    val description: String
)

@Composable
fun ConstraintLayoutEx(cardItem: CardItem) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (card, image, author, description) = createRefs()
        Card(
            modifier = Modifier
                .padding(10.dp)
                .constrainAs(card) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                },
            elevation = 8.dp
        ) {
            Image(
                painter = painterResource(id = cardItem.image),
                contentDescription = cardItem.imageDescription,
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .constrainAs(image) {
                        start.linkTo(card.start, margin = 12.dp)
                        top.linkTo(card.top, margin = 12.dp)
                    },
                contentScale = ContentScale.Crop
            )
            Text(
                text = cardItem.author,
                modifier = Modifier.constrainAs(author) {
                    start.linkTo(image.end, margin = 12.dp)
                    top.linkTo(image.top)
                }
            )
            Text(
                text = cardItem.description,
                modifier = Modifier.constrainAs(description) {
                    top.linkTo(author.bottom)
                    start.linkTo(author.start)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePracticeTheme {
        ConstraintLayoutEx(MainActivity.cardItem)
    }
}