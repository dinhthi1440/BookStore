package com.example.bookstore.data.repository.carts

import com.example.bookstore.base.DataResult
import com.example.bookstore.models.Book
import com.example.bookstore.models.Cart

interface ICartRepository {
    //suspend fun addNewCart(cart: Cart): DataResult<Int>
    suspend fun getCart(userID: String): DataResult<List<Cart>>
    suspend fun getBookByID(bookID: String): DataResult<Book>
}