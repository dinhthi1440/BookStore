package com.example.bookstore.data.repository.order

import com.example.bookstore.base.BaseReponsitory
import com.example.bookstore.base.DataResult
import com.example.bookstore.data.service.IFirebaseSource
import com.example.bookstore.models.Order

class OrderRepository(private val cloud: IFirebaseSource.CloudStore): BaseReponsitory(),IOrderRepository {
    override suspend fun getOrder(customerID: String): DataResult<List<Order>> {
        return getResult { cloud.getOrder(customerID) }
    }

    override suspend fun cancelOrder(customerID: String, order: Order): DataResult<Int> {
        return getResult { cloud.cancelOrder(customerID, order) }
    }

}