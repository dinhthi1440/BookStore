package com.example.bookstore.data.repository.address

import com.example.bookstore.base.BaseReponsitory
import com.example.bookstore.base.DataResult
import com.example.bookstore.data.service.IFirebaseSource
import com.example.bookstore.models.Address

class AddressRepository(private val cloud: IFirebaseSource.CloudStore): BaseReponsitory(), IAddressRepository {
    override suspend fun addNewAddress(userID: String, address: Address): DataResult<Int> {
        return getResult { cloud.addNewAddress(userID, address) }
    }

    override suspend fun getAddresses(userID: String): DataResult<List<Address>> {
        return getResult { cloud.getAddresses(userID) }
    }

    override suspend fun deleteAddress(userID: String, address: Address): DataResult<Int> {
        return getResult { cloud.deleteAddress(userID, address) }
    }
}