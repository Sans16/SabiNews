package com.test.sabinewsreader.di

import com.test.sabinewsreader.BuildConfig
import com.test.sabinewsreader.keys.ServerUtils
import com.test.sabinewsreader.data.generator.ServiceGenerator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder().client(okHttpClient)
            .baseUrl(ServerUtils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Provides
    @Singleton
    fun provideServiceClass(reBuilder: Retrofit.Builder): ServiceGenerator {
        return reBuilder.build().create(ServiceGenerator::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .also { client ->
                if (BuildConfig.DEBUG) client.addInterceptor(logging)
            }
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpLogging(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
}