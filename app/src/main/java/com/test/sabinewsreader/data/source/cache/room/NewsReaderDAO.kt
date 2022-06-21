package com.test.sabinewsreader.data.source.cache.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.sabinewsreader.model.NewsData
import com.test.sabinewsreader.data.Result

@Dao
interface NewsReaderDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsEntity(newsEntity: NewsEntity)

    @Query("DELETE FROM news_table")
    suspend fun deleteNewsEntities()

    @Query("SELECT * FROM news_table")
    fun observeNewsEntity(): LiveData<List<NewsEntity>>
}