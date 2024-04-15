package com.example.bookstore.data.repository.order

import com.example.bookstore.base.DataResult
import com.example.bookstore.models.Order

interface IOrderRepository {
    suspend fun getOrder(customerID: String): DataResult<List<Order>>
    suspend fun cancelOrder(customerID: String, order: Order): DataResult<Int>
}