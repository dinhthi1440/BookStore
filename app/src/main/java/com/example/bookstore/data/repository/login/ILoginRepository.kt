package com.example.bookstore.data.repository.login

import com.example.bookstore.base.DataResult

interface ILoginRepository {
    suspend fun loginByEmailPass(email: String, password: String): DataResult<String>
}