package com.example.bookstore.ui.change_password

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.data.repository.setting.ISettingRepository

class ChangePassViewModel(private val iSettingRepository: ISettingRepository):BaseViewModel() {
    private val _getUpdatePassResult = MutableLiveData<Int>()
    val getUpdatePassResult: LiveData<Int> get() = _getUpdatePassResult

    fun updatePassWord(newPassWord: String){
        executeTask(
            request = {iSettingRepository.updatePassWord(newPassWord)},
            onSuccess = {_getUpdatePassResult.value =it},
            onError = {}
        )
    }
}