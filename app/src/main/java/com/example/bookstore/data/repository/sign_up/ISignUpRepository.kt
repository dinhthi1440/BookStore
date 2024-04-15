package com.example.bookstore.data.repository.sign_up

import com.example.bookstore.base.DataResult
import com.example.bookstore.models.User

interface ISignUpRepository {
    suspend fun addUser(user: User): DataResult<Int>
}