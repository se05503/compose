package com.example.compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
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
                CatalogEx(itemList = itemDatas)
            }
        }
    }
}

@Composable
fun Item(ItemData: ItemData) {
    Card(elevation = 10.dp) {
        Column(modifier = Modifier.padding(20.dp)) {
            Image(
                painter = painterResource(id = ItemData.id),
                contentDescription = ItemData.title)
            Spacer(modifier = Modifier.size(12.dp))
            Text(text = ItemData.title, fontSize = 40.sp, style = TextStyle.Default)
            Spacer(modifier = Modifier.size(12.dp))
            Text(text = ItemData.description)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemPreview() {
    ComposePracticeTheme {
        Item(
            ItemData(
                id = R.drawable.a1,
                title = "해변 놀이 공원",
                description = "놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다. 놀이 공원 설명입니다."
            )
        )
    }
}

@Composable
fun CatalogEx(itemList: List<ItemData>) {
    LazyColumn {
        items(itemList) { data ->
            Item(data)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePracticeTheme {
        CatalogEx(itemList = itemDatas)
    }
}

val itemDatas : List<ItemData> = listOf(
    ItemData(R.drawable.a1,"첫번째 이미지", "첫번째 이미지 설명"),
    ItemData(R.drawable.a2,"두번째 이미지","두번째 이미지 설명"),
    ItemData(R.drawable.a3,"세번째 이미지","세번째 이미지 설명"),
    ItemData(R.drawable.a4,"네번째 이미지","네번째 이미지 설명"),
    ItemData(R.drawable.a5,"다섯번째 이미지","다섯번째 이미지 설명"),
    ItemData(R.drawable.a6,"여섯번째 이미지","여섯번째 이미지 설명"),
    ItemData(R.drawable.a7,"일곱번째 이미지","일곱번째 이미지 설명"),
    ItemData(R.drawable.a8,"여덟번째 이미지","여덟번째 이미지 설명"),
    ItemData(R.drawable.a9,"아홉번째 이미지","아홉번째 이미지 설명")
)



data class ItemData(
    val id: Int,
    val title: String,
    val description: String
)