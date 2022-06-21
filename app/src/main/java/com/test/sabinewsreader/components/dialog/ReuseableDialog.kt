package com.test.sabinewsreader.components.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ReuseableDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    title: String,
    description: String? = null,
    positiveAction: PositiveAction?,
    negativeAction: NegativeAction?
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        title = { Text(title) },
        text = {
            if (description != null) { Text(text = description) }
        },
        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.End,
            ) {
                if(negativeAction != null){
                    Button(
                        modifier = Modifier.padding(end = 8.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor
                        = MaterialTheme.colors.onError),
                        onClick = negativeAction.onNegativeAction
                    ) {
                        Text(text = negativeAction.negativeBtnTxt)
                    }
                }
                if(positiveAction != null){
                    Button(
                        modifier = Modifier.padding(end = 8.dp),
                        onClick = positiveAction.onPositiveAction,
                    ) {
                        Text(text = positiveAction.positiveBtnTxt,
                            color = MaterialTheme.colors.secondary)

                    }
                }
            }
        }
    )
}

data class PositiveAction(
    val positiveBtnTxt: String,
    val onPositiveAction: () -> Unit,
)

data class NegativeAction(
    val negativeBtnTxt: String,
    val onNegativeAction: () -> Unit,
)

class ReuseableDialogInfo private constructor(builder: Builder) {

    val title: String
    val onDismiss: () -> Unit
    val description: String?
    val positiveAction: PositiveAction?
    val negativeAction: NegativeAction?

    init {
        if(builder.title == null){
            throw Exception("GenericDialog title cannot be null.")
        }
        if(builder.onDismiss == null){
            throw Exception("GenericDialog onDismiss function cannot be null.")
        }
        this.title = builder.title!!
        this.onDismiss = builder.onDismiss!!
        this.description = builder.description
        this.positiveAction = builder.positiveCallOfAction
        this.negativeAction = builder.negativeCallOfAction
    }

    class Builder {

        var title: String? = null
            private set

        var onDismiss: (() -> Unit)? = null
            private set

        var description: String? = null
            private set

        var positiveCallOfAction: PositiveAction? = null
            private set

        var negativeCallOfAction: NegativeAction? = null
            private set

        fun title(title: String): Builder{
            this.title = title
            return this
        }

        fun onDismiss(onDismiss: () -> Unit): Builder{
            this.onDismiss = onDismiss
            return this
        }

        fun description(
            description: String
        ): Builder{
            this.description = description
            return this
        }

        fun positive(
            positiveAction: PositiveAction?,
        ) : Builder {
            this.positiveCallOfAction = positiveAction
            return this
        }

        fun build() = ReuseableDialogInfo(this)
    }
}
