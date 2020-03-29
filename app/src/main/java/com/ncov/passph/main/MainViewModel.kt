package com.ncov.passph.main

import androidx.lifecycle.ViewModel
import com.ncov.passph.core.coroutines.Coroutine
import com.ncov.passph.core.network.TaskStatus
import com.ncov.passph.core.network.clients.TestClient
import com.ncov.passph.core.utils.SingleLiveEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val testClient: TestClient
) : ViewModel() {

    val testEvent = SingleLiveEvent<TaskStatus<Any>>()

    fun test() {
        Coroutine.ioThenMainWithErrorHandler(work = {
            testClient.test("admin", "123456")
        }, callback = {
            testEvent.value = TaskStatus.success()
        }, errorHandler = CoroutineExceptionHandler { _, throwable ->
            testEvent.value = TaskStatus.failure(throwable)
        })
    }
}