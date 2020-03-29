package com.ncov.passph.core.coroutines

import kotlinx.coroutines.*

object Coroutine {

    fun main(work: suspend (() -> Unit)): Job =
        CoroutineScope(Dispatchers.Main).launch {
            work()
        }

    fun io(work: suspend (() -> Unit)): Job =
        CoroutineScope(Dispatchers.IO).launch {
            work()
        }

    fun <T : Any> ioThenMain(work: suspend (() -> T?), callback: ((T?) -> Unit)): Job =
        CoroutineScope(Dispatchers.Main).launch {
            val errorHandler = CoroutineExceptionHandler { _, throwable -> }
            val data = CoroutineScope(Dispatchers.IO).async(errorHandler) rt@{
                return@rt work()
            }.await()
            callback(data)
        }

    fun <T : Any> ioThenMainWithErrorHandler(errorHandler: CoroutineExceptionHandler, work: suspend (() -> T?), callback: ((T?) -> Unit)): Job =
        CoroutineScope(Dispatchers.Main).launch(errorHandler) {
            val data = CoroutineScope(Dispatchers.IO).async rt@{
                return@rt work()
            }.await()
            callback(data)
        }

    fun <T : Any> ioThenMainWithErrorHandler(
        errorCallback: ((error: Throwable) -> Unit),
        dispatcher: CoroutineDispatcher = Dispatchers.Main,
        work: suspend (() -> T?),
        callback: ((T?) -> Unit)
    ): Job {
        val errorHandler = CoroutineExceptionHandler { _, throwable ->
            CoroutineScope(dispatcher).launch {
                errorCallback(throwable)
            }
        }

        return CoroutineScope(Dispatchers.Main).launch(errorHandler) {
            val data = CoroutineScope(Dispatchers.IO).async rt@{
                return@rt work()
            }.await()
            callback(data)
        }
    }

    fun startTimer(delayMillis: Long = 0, repeatMillis: Long = 0, dispatcher: CoroutineDispatcher = Dispatchers.Main, action: () -> Unit) = GlobalScope.launch(Dispatchers.IO) {
        delay(delayMillis)
        if (repeatMillis > 0) {
            while (true) {
                withContext(dispatcher) {
                    action()
                }
                delay(repeatMillis)
            }
        } else {
            withContext(dispatcher) {
                action()
            }
        }
    }
}