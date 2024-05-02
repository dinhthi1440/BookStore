package com.example.bookstore.ui.notify

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.data.repository.notification.INotificationRepository
import com.example.bookstore.models.Address
import com.example.bookstore.models.Notify

class NotifyViewModel(private val iNotificationRepository: INotificationRepository): BaseViewModel() {
    private val _getNotifyResult = MutableLiveData<List<Notify>>()
    val getNotifyResult: LiveData<List<Notify>> get() = _getNotifyResult
    fun getNotify(userID: String){
        executeTask(
            request = {iNotificationRepository.getNotifications(userID)},
            onSuccess = {_getNotifyResult.value = it},
            onError = {}
        )
    }
}