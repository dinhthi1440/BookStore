package com.example.bookstore.data.repository.login

import com.example.bookstore.base.DataResult
import com.example.bookstore.models.User

interface ILoginRepository {
    suspend fun loginByEmailPass(email: String, password: String): DataResult<String>
    suspend fun getUser(userID: String):  DataResult<User>
}