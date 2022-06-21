package com.test.sabinewsreader.di

import com.test.sabinewsreader.data.source.repository.DefaultAppRepository
import com.test.sabinewsreader.data.source.repository.NewsReaderRepository
import com.test.sabinewsreader.data.source.SabiNewsReaderDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideAppRepository(
        @AppModule.AppRemoteDataSource remoteDataSource: SabiNewsReaderDataSource,
        @AppModule.AppLocalDataSource localDataSource: SabiNewsReaderDataSource
    ): NewsReaderRepository = DefaultAppRepository(remoteDataSource, localDataSource)
}