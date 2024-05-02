package com.example.bookstore.ui.new_address

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.data.repository.address.IAddressRepository
import com.example.bookstore.models.Address
import com.example.bookstore.models.User

class NewAddressViewModel(private val iAddressRepository: IAddressRepository):BaseViewModel() {
    private val _addNewAddressResult = MutableLiveData<Int>()
    val addNewAddressResult: LiveData<Int> get() = _addNewAddressResult
    fun addNewAddress(userID: String, address: Address){
        executeTask(
            request = {iAddressRepository.addNewAddress(userID, address)},
            onSuccess = {_addNewAddressResult.value = it},
            onError = {}
        )
    }
}