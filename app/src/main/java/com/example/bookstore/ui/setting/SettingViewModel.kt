package com.example.bookstore.ui.setting

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.data.repository.setting.ISettingRepository
import com.example.bookstore.models.User

class SettingViewModel(private val iSettingRepository: ISettingRepository):BaseViewModel() {

    private val _getUserResult = MutableLiveData<User>()
    val getUserResult: LiveData<User> get() = _getUserResult

    private val _getUpdateUserResult = MutableLiveData<Int>()
    val getUpdateUserResult: LiveData<Int> get() = _getUpdateUserResult

    private val _postImageResult = MutableLiveData<String>()
    val postImageResult: LiveData<String> get() = _postImageResult

    private val _getConfirmPassResult = MutableLiveData<Int>()
    val getConfirmPassResult: LiveData<Int> get() = _getConfirmPassResult
    fun getUser(userID: String){
        executeTask(
            request = {iSettingRepository.getUser(userID)},
            onSuccess = {_getUserResult.value = it},
            onError = {}
        )
    }

    fun updateUser(user: User){
        executeTask(
            request = {iSettingRepository.updateUser(user)},
            onSuccess = {_getUpdateUserResult.value = it},
            onError = {}
        )
    }

    fun upLoadImage(path: String, uri: Uri){
        executeTask(
            request = {iSettingRepository.upLoadImage(path, uri)},
            onSuccess = {_postImageResult.value = it},
            onError = {}
        )
    }

    fun reAuthenticateUser(currentPassword: String){
        executeTask(
            request = {iSettingRepository.reAuthenticateUser(currentPassword)},
            onSuccess = {_getConfirmPassResult.value = it},
            onError = {}
        )
    }
}