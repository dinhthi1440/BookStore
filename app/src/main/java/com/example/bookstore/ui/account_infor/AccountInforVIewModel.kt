package com.example.bookstore.ui.account_infor

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.data.repository.setting.ISettingRepository
import com.example.bookstore.models.User

class AccountInforVIewModel(private val iSettingRepository: ISettingRepository):BaseViewModel() {
    private val _getUpdateInforResult = MutableLiveData<Int>()
    val getUpdateInforResult: LiveData<Int> get() = _getUpdateInforResult
    fun updateInforUser(user: User){
        Log.e("TAG", "updateInforUser: haha", )
        executeTask(
            request = {iSettingRepository.updateInforUser(user)},
            onSuccess = {_getUpdateInforResult.value = it},
            onError = {}
        )
    }

}