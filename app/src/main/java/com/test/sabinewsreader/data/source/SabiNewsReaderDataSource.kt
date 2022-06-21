package com.test.sabinewsreader.data.source

import androidx.lifecycle.LiveData
import com.test.sabinewsreader.data.Result
import com.test.sabinewsreader.data.source.cache.room.NewsEntity
import com.test.sabinewsreader.model.NewsData
import kotlinx.coroutines.flow.Flow

interface SabiNewsReaderDataSource {
    fun observeNewsEntity(): LiveData<List<NewsEntity>>

    suspend fun saveNewsEntity(newsEntity: NewsEntity)

    suspend fun deleteNewsEntities()

    suspend fun getNewsFromAPI(apiKey: String): Flow<Result<List<NewsData>>>
}