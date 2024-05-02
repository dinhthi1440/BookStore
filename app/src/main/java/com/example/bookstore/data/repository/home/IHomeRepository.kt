package com.example.bookstore.data.repository.home

import com.example.bookstore.base.DataResult
import com.example.bookstore.models.Book
import com.example.bookstore.models.BookGenres
import com.example.bookstore.models.User

interface IHomeRepository {
    suspend fun getAllBook():DataResult<List<Book>>
    suspend fun pushGenreBook(): DataResult<Int>
    suspend fun getBookByGenre(): DataResult<List<BookGenres>>
    suspend fun addUser(user: User): DataResult<Int>
   suspend fun getUserByCusID(customerID: String): DataResult<User>
    suspend fun addFriends(userID: String, friendID: String): DataResult<Int>
}