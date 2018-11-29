package com.alvarohidalgo.heroesmvvm.ui.base.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.whileSelect

@ExperimentalCoroutinesApi
@JvmOverloads
suspend fun <T> ReceiveChannel<T>.debounce(delayTime: Long = 300L): ReceiveChannel<T> =
    Channel<T>(capacity = Channel.CONFLATED).also { channel ->
        coroutineScope {
            launch {
                var value = receive()
                whileSelect {
                    onTimeout(delayTime) {
                        channel.offer(value)
                        value = receive()
                        true
                    }

                    onReceive {
                        value = it
                        true
                    }
                }
            }
        }

    }

fun EditText.onTextChanged(): ReceiveChannel<String> = Channel<String>(Channel.UNLIMITED).also { channel ->
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            editable?.toString().orEmpty().let(channel::offer)
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

    })
}