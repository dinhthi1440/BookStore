package com.example.bookstore.ui.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.data.repository.order.IOrderRepository
import com.example.bookstore.models.Order

class OrderViewModel(private val iOrderRepository: IOrderRepository):BaseViewModel() {
    private val _getOrderResult = MutableLiveData<List<Order>>()
    val getOrderResult: LiveData<List<Order>> get() = _getOrderResult

    private val _getCancelOrderResult = MutableLiveData<Int>()
    val getCancelOrderResult: LiveData<Int> get() = _getCancelOrderResult
    fun getOrder(customerID: String){
        executeTask(
            request = {iOrderRepository.getOrder(customerID)},
            onSuccess = {
                _getOrderResult.value = it
            },
            onError = {

            }
        )
    }

    fun cancelOrder(customerID: String, order: Order){
        executeTask(
            request = {iOrderRepository.cancelOrder(customerID, order)},
            onSuccess = {_getCancelOrderResult.value = it},
            onError = {}
        )
    }
}