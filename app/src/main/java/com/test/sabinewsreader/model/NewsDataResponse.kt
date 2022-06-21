package com.test.sabinewsreader.model

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @field:SerializedName("status") val status: String,
    @field:SerializedName("totalResults") val totalResults: Int,
    @field:SerializedName("articles") val results: List<NewsData>,
    @field:SerializedName("message") val message: String
)