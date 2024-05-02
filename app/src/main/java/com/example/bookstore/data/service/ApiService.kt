package com.example.bookstore.data.service

interface ApiService {

    interface Provinces{
        suspend fun getAllProvince(): List<String>
    }
}