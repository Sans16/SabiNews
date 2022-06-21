package com.test.sabinewsreader.data.source.cache.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.sabinewsreader.data.source.cache.room.NewsEntity
import com.test.sabinewsreader.data.source.cache.room.NewsReaderDAO

@Database(
    version = 1,
    entities = [ NewsEntity::class ]
)
abstract class SabiNewsReaderDatabase : RoomDatabase() { abstract fun newsReaderDAO(): NewsReaderDAO }