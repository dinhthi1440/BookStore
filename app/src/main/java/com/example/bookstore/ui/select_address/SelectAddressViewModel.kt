package com.example.bookstore.ui.select_address

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.data.repository.address.IAddressRepository
import com.example.bookstore.models.Address

class SelectAddressViewModel(private val iAddressRepository: IAddressRepository):BaseViewModel() {
    private val _getAddressesResult = MutableLiveData<List<Address>>()
    val getAddressesResult: LiveData<List<Address>> get() = _getAddressesResult

    private val _deleteAddressResult = MutableLiveData<Int>()
    val deleteAddressResult: LiveData<Int> get() = _deleteAddressResult
    fun getAddresses(userID: String){
        executeTask(
            request = {iAddressRepository.getAddresses(userID)},
            onSuccess = {_getAddressesResult.value = it},
            onError = {}
        )
    }


    fun deleteAddress(userID: String, address: Address){
        executeTask(
            request = {iAddressRepository.deleteAddress(userID, address)},
            onSuccess = {_deleteAddressResult.value = it},
            onError = {}
        )
    }
}