package com.example.bookstore.data.datasource.home

import com.example.bookstore.models.Book

interface IHomeDataSource {
    interface Remote{
        suspend fun getAllBook(): List<Book>
    }
}