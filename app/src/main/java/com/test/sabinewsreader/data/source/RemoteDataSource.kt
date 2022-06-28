package com.test.sabinewsreader.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.sabinewsreader.data.source.cache.room.NewsEntity
import com.test.sabinewsreader.data.Result
import com.test.sabinewsreader.data.generator.ServiceGenerator
import com.test.sabinewsreader.keys.ServerUtils
import com.test.sabinewsreader.model.NewsData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataSource internal constructor(
    private val service: ServiceGenerator
): SabiNewsReaderDataSource {

    override fun observeNewsEntity(): LiveData<List<NewsEntity>> = MutableLiveData()

    override suspend fun saveNewsEntity(newsEntity: NewsEntity) {/*EMPTY BLOCK*/}

    override suspend fun deleteNewsEntities()  {/*EMPTY BLOCK*/}

    override suspend fun getNewsFromAPI(apiKey: String): Flow<Result<List<NewsData>>> = flow {
        emit(Result.Loading)
        try {
            val result = service.getNewsData(apiKey = apiKey)
            if (result.status == ServerUtils.OK_STATUS) emit(Result.Success(result.results))
            else emit(Result.Error(Throwable(result.message)))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    private fun unknown(){

    }
}