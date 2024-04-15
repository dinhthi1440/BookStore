package com.example.bookstore.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.lang.Exception

abstract class BaseViewModel: ViewModel() {
    private val loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = loading
    private val _messageError = MutableLiveData<String>()
    val messageError: LiveData<String>
        get() = _messageError

    protected fun<T> executeTask(
        request: suspend CoroutineScope.() -> DataResult<T>,
        onSuccess: (T) -> Unit,
        onError: (Exception) -> Unit,
        loadingInvisible: Boolean = true
    ){
        if (loadingInvisible) showLoading()
        viewModelScope.launch {
            when (val response = request(this)){
                is DataResult.Success -> {
                    onSuccess(response.data)
                    hideLoading()
                }
                is DataResult.Error -> {
                    onError(response.exception)
                    hideLoading()
                }
                else -> {}
            }
        }
    }
    private fun showLoading() {
        loading.value = true
    }

    private fun hideLoading() {
        loading.value = false
    }


}