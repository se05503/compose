package com.example.compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.example.compose_practice.ui.theme.ComposePracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePracticeTheme {
                // Surface 로 감싸주는 것이 좋다.
                Surface(
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

    val constraintSet = ConstraintSet {
        val image = createRefFor("image")
        val author = createRefFor("author")
        val description = createRefFor("description")

        constrain(image) {
            centerVerticallyTo(parent)
        }

        constrain(author) {
            start.linkTo(image.end, margin = 6.dp)
            top.linkTo(parent.top)
        }

        constrain(description) {
            top.linkTo(author.bottom)
            start.linkTo(author.start)
        }
    }

    Card(
        modifier = Modifier.padding(10.dp),
        elevation = 12.dp
    ) {
        ConstraintLayout(
            constraintSet,
            modifier = Modifier.padding(12.dp) // dp 값 커지면 레이아웃 card 밖으로 튀어나감. text 를 card 안에 넣었는데 왜 밖으로 튀어나가는지 모르겠음
        ) {
            Image(
                painter = painterResource(id = cardItem.image),
                contentDescription = cardItem.imageDescription,
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .layoutId("image"),
                contentScale = ContentScale.Crop
            )
            Text(
                text = cardItem.author,
                modifier = Modifier.layoutId("author")
            )
            Text(
                text = cardItem.description,
                modifier = Modifier.layoutId("description")
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