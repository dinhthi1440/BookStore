package com.example.bookstore.data.repository.address

import com.example.bookstore.base.BaseReponsitory
import com.example.bookstore.base.DataResult
import com.example.bookstore.data.service.IFirebaseSource
import com.example.bookstore.models.Address

interface IAddressRepository {
    suspend fun addNewAddress(userID: String, address: Address): DataResult<Int>
    suspend fun getAddresses(userID: String): DataResult<List<Address>>
    suspend fun deleteAddress(userID: String, address: Address): DataResult<Int>
}