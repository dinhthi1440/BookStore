package com.example.bookstore.data.repository.login

import com.example.bookstore.base.BaseReponsitory
import com.example.bookstore.base.DataResult
import com.example.bookstore.data.service.IFirebaseSource

class LoginRepository(private val auth: IFirebaseSource.Authentication): BaseReponsitory(), ILoginRepository {
    override suspend fun loginByEmailPass(email: String, password: String): DataResult<String> {
        return getResult { auth.loginByEmailPass(email, password) }
    }
}