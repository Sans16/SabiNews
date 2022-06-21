package com.test.sabinewsreader.data.source.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.sabinewsreader.data.source.cache.room.NewsEntity
import com.test.sabinewsreader.data.Result
import com.test.sabinewsreader.data.source.SabiNewsReaderDataSource
import com.test.sabinewsreader.model.NewsData
import kotlinx.coroutines.flow.Flow

class DefaultAppRepository(
    private val remoteDataSource: SabiNewsReaderDataSource,
    private val localDataSource: SabiNewsReaderDataSource
): NewsReaderRepository {

    override fun observeNewsEntity(): LiveData<List<NewsEntity>> =
    localDataSource.observeNewsEntity()

    override suspend fun saveNewsEntity(newsEntity: NewsEntity) =
        localDataSource.saveNewsEntity(newsEntity)

    override suspend fun deleteNewsEntities() = localDataSource.deleteNewsEntities()

    override suspend fun getNewsFromAPI(apiKey: String): Flow<Result<List<NewsData>>> =
        remoteDataSource.getNewsFromAPI(apiKey)
}