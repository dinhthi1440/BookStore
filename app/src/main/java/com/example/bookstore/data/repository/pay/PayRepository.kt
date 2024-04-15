package com.example.bookstore.data.repository.pay

import com.example.bookstore.base.BaseReponsitory
import com.example.bookstore.base.DataResult
import com.example.bookstore.data.service.IFirebaseSource
import com.example.bookstore.models.Order

class PayRepository(private val cloud: IFirebaseSource.CloudStore): BaseReponsitory(), IPayRepository {
    override suspend fun addNewOrder(order: Order): DataResult<Int> {
        return getResult { cloud.addNewOrder(order) }
    }

    override suspend fun deleteCarts(userID: String, listCardID: List<String>): DataResult<Int> {
        return getResult { cloud.deleteCarts(userID, listCardID) }
    }
}