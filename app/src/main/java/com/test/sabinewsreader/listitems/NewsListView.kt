package com.test.sabinewsreader

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.sabinewsreader.keys.DataKeys
import com.test.sabinewsreader.model.NewsData

@Composable
fun NewsListView(
    data: List<NewsData>,
    onNavigateToBrowser: (String?) -> Unit){
    Box(modifier = Modifier.background(color = MaterialTheme.colors.surface)) {
        if (data.isEmpty()) {
            EmptyView()
        } else {
            Column(){
                Text(
                    modifier = Modifier.padding(8.dp)
                        .align(Alignment.CenterHorizontally),
                    text = DataKeys.TOP_NEWS,
                    maxLines = 1,
                    color = MaterialTheme.colors.onBackground,
                    style = TextStyle(fontSize = 14.sp)
                )
                LazyColumn(contentPadding = PaddingValues(top = 2.dp, bottom = 2.dp)) {
                    itemsIndexed(items = data) { _, newsItem ->
                        NewsCardView(
                            newsItem = newsItem,
                            onClick = { onNavigateToBrowser(newsItem.url) }
                        )
                    }
                }
            }

        }
    }
}