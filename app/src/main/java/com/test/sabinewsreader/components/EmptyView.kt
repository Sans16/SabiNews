package com.test.sabinewsreader

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun EmptyView() {
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column(modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally){

            val image: Painter = painterResource(id = R.drawable.empty_news_icon)
            Image(contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(150.dp),
                painter = image,contentDescription = "News Feed Empty Image View")

            Text(
                modifier = Modifier.padding(8.dp)
                    .align(Alignment.CenterHorizontally),
                text = "No News Available",
                style = TextStyle(fontSize = 30.sp)
            )

            Text(
                modifier = Modifier.padding(8.dp)
                    .align(Alignment.CenterHorizontally),
                text = "You currently have no news item to view",
                style = TextStyle(fontSize = 14.sp)
            )
        }
    }
}