package com.example.bookstore.data.repository.home

import com.example.bookstore.base.BaseReponsitory
import com.example.bookstore.base.DataResult
import com.example.bookstore.data.service.IFirebaseSource
import com.example.bookstore.models.Book
import com.example.bookstore.models.BookGenres
import com.example.bookstore.models.User

class HomeRepository(private val cloud: IFirebaseSource.CloudStore): BaseReponsitory(), IHomeRepository {
    override suspend fun getAllBook(): DataResult<List<Book>> {
        return getResult { cloud.getAllBook() }
    }

    override suspend fun pushGenreBook(): DataResult<Int> {
        return getResult { cloud.pushGenreBook() }
    }

    override suspend fun getBookByGenre(): DataResult<List<BookGenres>> {
        return getResult { cloud.getBookByGenre() }
    }

    override suspend fun addUser(user: User): DataResult<Int> {
        return getResult { cloud.addUser(user) }
    }

}