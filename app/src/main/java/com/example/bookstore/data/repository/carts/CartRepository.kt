package com.example.bookstore.data.repository.carts

import com.example.bookstore.base.BaseReponsitory
import com.example.bookstore.base.DataResult
import com.example.bookstore.data.service.IFirebaseSource
import com.example.bookstore.models.Book
import com.example.bookstore.models.Cart

class CartRepository(private val cloud: IFirebaseSource.CloudStore): BaseReponsitory(), ICartRepository {
    //    override suspend fun addNewCart(cart: Cart): DataResult<Int> {
//        return getResult { cloud.addNewCart(cart) }
//    }
    override suspend fun getCart(userID: String): DataResult<List<Cart>> {
        return getResult { cloud.getCart(userID) }
    }

    override suspend fun getBookByID(bookID: String): DataResult<Book> {
        return getResult { cloud.getBookByID(bookID) }
    }
}