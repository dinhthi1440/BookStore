package com.example.bookstore.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.data.repository.sign_up.ISignUpRepository
import com.example.bookstore.models.Comment
import com.example.bookstore.models.User

class SingUpViewModel(private val iSingUpViewModel: ISignUpRepository):BaseViewModel() {
    private val _getAddResult = MutableLiveData<Int>()
    val getAddResult: LiveData<Int> get() = _getAddResult
    fun addUser(user: User){
        executeTask(
            request = {iSingUpViewModel.addUser(user)},
            onSuccess = {_getAddResult.value = it},
            onError = {}
        )
    }
}