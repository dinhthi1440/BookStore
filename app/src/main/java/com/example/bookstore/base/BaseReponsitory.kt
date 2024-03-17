package com.example.bookstore.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class BaseReponsitory {
    suspend fun <T> getResult(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        request: suspend CoroutineScope.() -> T
    ): DataResult<T> {
        return withContext(dispatcher) {
            try {
                DataResult.Success(request())
            } catch (e: Exception) {
                DataResult.Error(e)
            }
        }
    }
}