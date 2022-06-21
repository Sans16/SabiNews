package com.test.sabinewsreader.di

import android.content.Context
import androidx.room.Room
import com.test.sabinewsreader.keys.ServerUtils.DATABASE_NAME
import com.test.sabinewsreader.data.source.cache.room.NewsReaderDAO
import com.test.sabinewsreader.data.source.cache.room.SabiNewsReaderDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): SabiNewsReaderDatabase {
        return Room.databaseBuilder(context, SabiNewsReaderDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideAppDAO(roomDatabase: SabiNewsReaderDatabase): NewsReaderDAO = roomDatabase.newsReaderDAO()
}