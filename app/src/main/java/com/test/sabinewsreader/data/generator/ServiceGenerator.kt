package com.test.sabinewsreader.data.generator

import com.test.sabinewsreader.keys.ServerUtils
import com.test.sabinewsreader.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceGenerator {
    @GET("top-headlines")
    suspend fun getNewsData(@Query(ServerUtils.COUNTRY) country: String = "us",
                            @Query(ServerUtils.API_KEY) apiKey: String): NewsResponse
}