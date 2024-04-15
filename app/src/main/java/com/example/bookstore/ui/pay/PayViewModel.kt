package com.example.bookstore.ui.pay

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.data.repository.pay.IPayRepository
import com.example.bookstore.models.Order
import com.example.bookstore.models.Voucher

class PayViewModel(private val iPayRepository: IPayRepository):BaseViewModel() {

    private val _getAddResult = MutableLiveData<Int>()
    val getAddResult: LiveData<Int> get() = _getAddResult
    private val _getDeleteCartResult = MutableLiveData<Int>()
    val getDeleteCartResult: LiveData<Int> get() = _getDeleteCartResult
    fun addNewOrder(order: Order){
        executeTask(
            request = {iPayRepository.addNewOrder(order)},
            onSuccess = {
                _getAddResult.value = 1
            },
            onError = {

            }
        )
    }

    fun deleteCarts(userID: String, listCartID: List<String>){
        executeTask(
            request = {iPayRepository.deleteCarts(userID, listCartID)},
            onSuccess = {_getDeleteCartResult.value = it},
            onError = {}
        )
    }
}