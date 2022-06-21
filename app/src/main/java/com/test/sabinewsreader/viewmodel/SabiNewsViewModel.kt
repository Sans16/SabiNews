package com.test.sabinewsreader.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.test.sabinewsreader.BuildConfig
import com.test.sabinewsreader.data.source.repository.NewsReaderRepository
import com.test.sabinewsreader.data.Result
import com.test.sabinewsreader.data.mapper.CacheMapper
import com.test.sabinewsreader.helpers.DialogQueue
import com.test.sabinewsreader.keys.ServerUtils
import com.test.sabinewsreader.model.NewsData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.test.sabinewsreader.utils.CleanUpUtils

@HiltViewModel
class SabiNewsViewModel  @Inject constructor(
    private val cacheMapper: CacheMapper,
    private val repository: NewsReaderRepository
) : ViewModel() {
    val newsList: MutableState<List<NewsData>> = mutableStateOf(ArrayList())
    val onLoad: MutableState<Boolean> = mutableStateOf(false)
    val loading = mutableStateOf(false)
    val dialogQueue = DialogQueue()

//    private fun saveNewsToRoom(news: List<NewsData>) {
//        viewModelScope.launch(Dispatchers.IO) {
//            news.forEach {repository.saveNewsEntity(cacheMapper.mapToEntity(it))}
//        }
//    }

    fun getData() {
        viewModelScope.launch {
            repository.getNewsFromAPI(BuildConfig.API_KEY)
                .onEach { it ->
                    when(it) {
                        is Result.Success<List<NewsData>> -> {
                            loading.value = false
                           it.data?.let {
                                   data -> newsList.value = data
                                   //saveNewsToRoom(data)
                           }
                        }
                        is Result.Error -> {
                            loading.value = false
                            dialogQueue.showErrorMessage(ServerUtils.DEFAULT_ERROR_MESSAGE, it.exception.localizedMessage!!)
                        }
                        is Result.Loading -> loading.value = true
                    }
                }
                .launchIn(viewModelScope)
        }
    }
}