package com.example.bookstore.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.lang.Exception

abstract class BaseViewModel: ViewModel() {
    protected val loading: MutableLiveData<Boolean> = MutableLiveData(false)
    private val _messageError = MutableLiveData<String>()  //set
    val messageError: LiveData<String>  //get
        get() = _messageError
    protected fun<T> executeTask(
        request: suspend CoroutineScope.() -> DataResult<T>,
        onSuccess: (T) -> Unit,
        onError: (Exception) -> Unit
    ){
        viewModelScope.launch {
            when (val response = request(this)){
                is DataResult.Success -> {
                    onSuccess(response.data)

                }
                is DataResult.Error -> {
                    onError(response.exception)
                }
                else -> {}
        }
        }
    }



}