package com.example.bookstore.data.repository.sign_up

import com.example.bookstore.base.BaseReponsitory
import com.example.bookstore.base.DataResult
import com.example.bookstore.data.service.IFirebaseSource
import com.example.bookstore.models.User

class SignUpRepository(private val cloud: IFirebaseSource.CloudStore): BaseReponsitory(), ISignUpRepository {
    override suspend fun addUser(user: User): DataResult<Int> {
        return getResult { cloud.addUser(user) }
    }
}