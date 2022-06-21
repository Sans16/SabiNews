package com.test.sabinewsreader.helpers

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.test.sabinewsreader.components.dialog.PositiveAction
import com.test.sabinewsreader.components.dialog.ReuseableDialogInfo
import com.test.sabinewsreader.keys.DataKeys
import java.util.*

class DialogQueue {

    //FIFO behavior
    val queue: MutableState<Queue<ReuseableDialogInfo>> = mutableStateOf(LinkedList())

    private fun clearTitle(){
        if (queue.value.isNotEmpty()) {
            val update = queue.value
            update.remove()
            queue.value = ArrayDeque()
            queue.value = update
        }
    }

    fun showErrorMessage(title: String, description: String){
        queue.value.offer(
            ReuseableDialogInfo.Builder()
                .title(title)
                .onDismiss(this::clearTitle)
                .description(description)
                .positive(
                    PositiveAction(
                        positiveBtnTxt = DataKeys.DONE,
                        onPositiveAction = this::clearTitle,
                    )
                )
                .build()
        )
    }
}