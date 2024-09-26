package com.example.compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
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
            "https://cdn.pixabay.com/photo/2015/04/23/22/00/flowers-736885_960_720.jpg",
            "엔텔로프 캐년",
            "Dalinaum",
            "엔텔로프 캐년은 죽기 전에 꼭 봐야할 정경으로 소개되었습니다."
        )
    }
}

data class CardItem(
    val image: String,
    val imageDescription: String,
    val author: String,
    val description: String
)

@Composable
fun ConstraintLayoutEx(cardItem: CardItem) {
    val placeHolderColor = Color(0x28000000)
    Card(
        modifier = Modifier.padding(10.dp),
        elevation = 12.dp
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()// dp 값 커지면 레이아웃 card 밖으로 튀어나감. text 를 card 안에 넣었는데 왜 밖으로 튀어나가는지 모르겠음
        ) {
            val (image, author, description) = createRefs()
            AsyncImage(
                model = cardItem.image,
                contentDescription = cardItem.imageDescription,
                contentScale = ContentScale.Crop,
                placeholder = ColorPainter(placeHolderColor),
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .constrainAs(image) {
                        centerVerticallyTo(parent) // linkTo(parent.top, parent.bottom) 도 가능함
                        start.linkTo(parent.start, margin = 8.dp)
                    }
            )
            Text(
                text = cardItem.author,
                modifier = Modifier.constrainAs(author) {
                    linkTo(image.end, parent.end, startMargin = 8.dp, endMargin = 8.dp) // 해당 범위를 넘어가지 않는다. -> 이전에 padding 이 커졌을 때 레이아웃이 나가는 문제점 해결
                    width = Dimension.fillToConstraints
                }
            )
            Text(
                text = cardItem.description,
                modifier = Modifier.constrainAs(description) {
                    linkTo(image.end, parent.end, startMargin = 8.dp, endMargin = 8.dp)
                    width = Dimension.fillToConstraints
                }
            )
            //
            val chain = createVerticalChain(author, description, chainStyle = ChainStyle.Packed )
            constrain(chain) {
                top.linkTo(parent.top, margin = 8.dp)
                bottom.linkTo(parent.bottom, margin = 8.dp)
            }
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