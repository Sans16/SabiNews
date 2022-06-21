package com.test.sabinewsreader

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewModelScope
import com.test.sabinewsreader.keys.DataKeys
import com.test.sabinewsreader.keys.ServerUtils
import com.test.sabinewsreader.ui.theme.SabiNewsReaderTheme
import com.test.sabinewsreader.viewmodel.SabiNewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: SabiNewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent(
                viewModel,
                openCustomBrowser = { url -> url?.let { loadWebUrl(it) } }
            )
        }
    }

    private fun loadWebUrl(url: String) {
        val customTabsIntent = CustomTabsIntent.Builder()
            .setColorScheme(CustomTabsIntent.COLOR_SCHEME_SYSTEM)
            .build()

        try { customTabsIntent.launchUrl(this, Uri.parse(url)) }
        catch (e: Exception) {  Timber.e(e) }
    }
}

@Composable
fun MainContent(
    viewModel: SabiNewsViewModel,
    openCustomBrowser: (String?) -> Unit,
) {

    val onLoad = viewModel.onLoad.value
    if (!onLoad) {
        viewModel.onLoad.value = true
        viewModel.getData()
    }


    val data = viewModel.newsList.value

    val loading = viewModel.loading.value

    val dialogQueue = viewModel.dialogQueue

    SabiNewsReaderTheme (dialogQueue = dialogQueue.queue.value) {
        Scaffold (
            topBar = { TopAppBar(title = { Text(text = DataKeys.PAGE_TITLE,
                color = MaterialTheme.colors.onSurface)},
                backgroundColor = MaterialTheme.colors.surface)  },
            content = {
                Column(verticalArrangement = Arrangement.Center) {
                    if (loading) {
                        LinearProgressIndicator(Modifier.fillMaxWidth())
                    }
                    //viewModel.saveNewsToRoom(data)
                    NewsListView(data = data, onNavigateToBrowser = openCustomBrowser)
                }
            }
        )
    }

}



