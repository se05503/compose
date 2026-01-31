package com.example.compose_practice.ui.components.movie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_practice.R
import com.example.compose_practice.ui.theme.Paddings

private val ICON_SIZE = 12.dp

@Composable
fun MovieItem() {
    Column(
        modifier = Modifier
            .width(150.dp)
            .padding(Paddings.large)
    ) {
        Poster(
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "영화 제목입니다. 영화 제목입니다.",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis, // 글자의 길이가 길때 끝에 ... 표시
            modifier = Modifier.padding(top = Paddings.large),
            style = MaterialTheme.typography.body2
        )
        Row(
            modifier = Modifier.padding(vertical = Paddings.medium),
            verticalAlignment = Alignment.CenterVertically // Alignment = 다른 방향, Arrangement = 같은 방향
        ) {
            Icon(
                modifier = Modifier.padding(Paddings.small).size(ICON_SIZE),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_star),
                tint = Color.Black.copy(alpha = 0.5f),
                contentDescription = "영화에 대한 평점" // 시각 장애인을 위한 청각 서비스를 제공할 때 읽어줌. 아이콘, 이미지에 무조건 넣어줘야함
            )
            Text(
                text = "5.0",
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
            )
        }
    }
}

// 따로 컴포넌트로 분리. 해당 부분을 눌러서 상세 정보로 이동하기 때문
@Composable
fun Poster(
    modifier: Modifier
) {
    Card( // 모서리 둥글게 함
        modifier.height(200.dp)
    ) {
        Box(
            modifier = Modifier.background(Color.Blue)
        )
    }
}

@Preview
@Composable
fun MovieItemPreview() {
    MovieItem()
}