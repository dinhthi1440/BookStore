package com.example.bookstore.data.repository.setting

import android.net.Uri
import com.example.bookstore.base.BaseReponsitory
import com.example.bookstore.base.DataResult
import com.example.bookstore.data.service.IFirebaseSource
import com.example.bookstore.models.User

class SettingRepository(private val cloud: IFirebaseSource.CloudStore,
    private val storage: IFirebaseSource.Storage,
    private val auth: IFirebaseSource.Authentication): BaseReponsitory(), ISettingRepository {
    override suspend fun getUser(userID: String): DataResult<User> {
        return getResult { cloud.getUser(userID) }
    }

    override suspend fun updateUser(user: User): DataResult<Int> {
        return getResult { cloud.updateUser(user) }
    }

    override suspend fun upLoadImage(path: String, uri: Uri): DataResult<String> {
        return getResult { storage.upLoadImage(path, uri) }
    }

    override suspend fun updateInforUser(user: User): DataResult<Int> {
        return getResult { cloud.updateInforUser(user) }
    }

    override suspend fun reAuthenticateUser(currentPassword: String): DataResult<Int> {
        return getResult {auth.reAuthenticateUser(currentPassword) }
    }

    override suspend fun updatePassWord(newPassword: String): DataResult<Int> {
        return getResult { auth.updatePassWord(newPassword) }
    }
}