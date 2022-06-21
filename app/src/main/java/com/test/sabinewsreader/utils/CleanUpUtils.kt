package com.test.sabinewsreader.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.sabinewsreader.model.NewsData
import com.test.sabinewsreader.data.Result

object CleanUpUtils {
    fun cleanUpNewsEntity(tasksResult: Result<List<NewsData>>): LiveData<List<NewsData>> {
        val result = MutableLiveData<List<NewsData>>()
        if (tasksResult is Result.Success) result.value = tasksResult.data!!
        else result.value = emptyList()
        return result
    }
}