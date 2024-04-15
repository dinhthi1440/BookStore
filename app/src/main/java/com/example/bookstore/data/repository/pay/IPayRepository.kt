package com.example.bookstore.data.repository.pay

import com.example.bookstore.base.DataResult
import com.example.bookstore.models.Order

interface IPayRepository {
    suspend fun addNewOrder(order: Order): DataResult<Int>
    suspend fun deleteCarts(userID: String, listCardID: List<String>): DataResult<Int>
}