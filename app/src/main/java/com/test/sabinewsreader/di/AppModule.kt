package com.test.sabinewsreader.di

import com.test.sabinewsreader.data.source.RemoteDataSource
import com.test.sabinewsreader.data.source.SabiNewsReaderDataSource
import com.test.sabinewsreader.data.generator.ServiceGenerator
import com.test.sabinewsreader.data.source.cache.LocalDataSource
import com.test.sabinewsreader.data.source.cache.room.NewsReaderDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class AppRemoteDataSource

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class AppLocalDataSource

    @Singleton
    @AppRemoteDataSource
    @Provides
    fun provideRemoteDataSource(service: ServiceGenerator): SabiNewsReaderDataSource = RemoteDataSource(service)

    @Singleton
    @AppLocalDataSource
    @Provides
    fun provideLocalDataSource(newsReaderDAO: NewsReaderDAO): SabiNewsReaderDataSource = LocalDataSource(newsReaderDAO)
}