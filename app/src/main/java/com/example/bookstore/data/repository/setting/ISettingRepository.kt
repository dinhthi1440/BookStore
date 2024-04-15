package com.example.bookstore.data.repository.setting

import android.net.Uri
import com.example.bookstore.base.DataResult
import com.example.bookstore.models.User


interface ISettingRepository {
    suspend fun getUser(userID: String): DataResult<User>
    suspend fun updateUser(user: User): DataResult<Int>
    suspend fun upLoadImage(path: String, uri: Uri): DataResult<String>
    suspend fun updateInforUser(user: User): DataResult<Int>
    suspend fun reAuthenticateUser(currentPassword: String): DataResult<Int>
    suspend fun updatePassWord(newPassword: String): DataResult<Int>
}