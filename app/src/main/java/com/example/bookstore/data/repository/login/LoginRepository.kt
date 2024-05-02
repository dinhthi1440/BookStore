package com.example.bookstore.data.repository.login

import com.example.bookstore.base.BaseReponsitory
import com.example.bookstore.base.DataResult
import com.example.bookstore.data.service.IFirebaseSource
import com.example.bookstore.models.User

class LoginRepository(private val auth: IFirebaseSource.Authentication, private val cloud: IFirebaseSource.CloudStore): BaseReponsitory(), ILoginRepository {
    override suspend fun loginByEmailPass(email: String, password: String): DataResult<String> {
        return getResult { auth.loginByEmailPass(email, password) }
    }

    override suspend fun getUser(userID: String): DataResult<User> {
        return getResult { cloud.getUser(userID)}
    }
}