package com.example.bookstore.data.datasource.home

import com.example.bookstore.data.service.IFirebaseSource
import com.example.bookstore.models.Book

class HomeDataSource(private val firebaseService: IFirebaseSource.CloudStore): IHomeDataSource.Remote {
    override suspend fun getAllBook(): List<Book> {
        return firebaseService.getAllBook()
    }

}