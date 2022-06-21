package com.test.sabinewsreader.data.source.cache

import androidx.lifecycle.LiveData
import com.test.sabinewsreader.data.source.cache.room.NewsEntity
import com.test.sabinewsreader.data.source.SabiNewsReaderDataSource
import com.test.sabinewsreader.model.NewsData
import kotlinx.coroutines.flow.Flow
import com.test.sabinewsreader.data.Result
import com.test.sabinewsreader.data.source.cache.room.NewsReaderDAO
import kotlinx.coroutines.flow.flow

class LocalDataSource internal constructor(
    private val newsReaderDAO: NewsReaderDAO
) : SabiNewsReaderDataSource {

    override fun observeNewsEntity(): LiveData<List<NewsEntity>> {
        return newsReaderDAO.observeNewsEntity()
    }

    override suspend fun saveNewsEntity(newsEntity: NewsEntity) {
            newsReaderDAO.insertNewsEntity(newsEntity)
        }

        override suspend fun deleteNewsEntities() {
            newsReaderDAO.deleteNewsEntities()
        }

        override suspend fun getNewsFromAPI(apiKey: String): Flow<Result<List<NewsData>>> = flow {
            emit(Result.Error(Throwable("Local Doesn't implement this method")))
        }
}