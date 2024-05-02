package com.example.bookstore.ui.login

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.example.bookstore.R
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.data.repository.login.ILoginRepository
import com.example.bookstore.extensions.openDlSuccess
import com.example.bookstore.models.Order
import com.example.bookstore.models.User

class LoginViewModel(private val iLoginRepository: ILoginRepository):BaseViewModel() {
    private val _getUserResult = MutableLiveData<User>()
    val getUserResult: LiveData<User> get() = _getUserResult
    fun getUser(userID: String){
        executeTask(
            request = {iLoginRepository.getUser(userID)},
            onSuccess = {_getUserResult.value= it},
            onError = {}
        )
    }
}