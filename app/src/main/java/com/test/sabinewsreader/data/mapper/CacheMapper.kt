package com.test.sabinewsreader.data.mapper

import com.test.sabinewsreader.data.source.cache.room.NewsEntity
import com.test.sabinewsreader.model.NewsData
import javax.inject.Inject

class CacheMapper @Inject constructor() {
    fun mapToEntity(data: NewsData): NewsEntity {
        return NewsEntity(
            author = data.author ?: "",
            title = data.title ?: "",
            description = data.description ?: "",
            url = data.url ?: "",
            urlToImage = data.urlToImage ?: "",
            publishedAt = data.publishedAt ?: "",
            content = data.content ?: ""
        )
    }
}