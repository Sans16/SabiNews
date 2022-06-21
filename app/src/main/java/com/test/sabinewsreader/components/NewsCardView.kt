package com.test.sabinewsreader

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.coil.rememberCoilPainter
import com.test.sabinewsreader.model.NewsData
import com.test.sabinewsreader.ui.theme.AppGrey

@Composable
fun NewsCardView(
    newsItem: NewsData,
    onClick: () -> Unit,
) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .clickable(onClick = onClick)
    ) {

        Box(Modifier.fillMaxWidth()) {
            Image(
                painter = rememberCoilPainter(newsItem.urlToImage),
                contentDescription = newsItem.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )

            //Overlay View
            Surface(
                color = Color.Black,
                modifier = Modifier.alpha(0.75f)
            ) {
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp))
            }

            Column(modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(6.dp)) {

                Text(
                    text = newsItem.title ?: "",
                    maxLines = 2,
                    fontSize = 18.sp,
                    color = Color.White,
                    style = MaterialTheme.typography.h3
                )

                Spacer(modifier = Modifier.size(8.dp))

                Text(
                    text = newsItem.author ?: "",
                    maxLines = 1,
                    color = AppGrey,
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewLayout(){
    val sampleItem = NewsData("Sanusi Sheriff","This is the best news I have heard today. Please click to read more",
        "Short bio that is not visible","https://www.bbc.com",
        "unknown","67484","784")
    NewsCardView(newsItem = sampleItem) {}
}
