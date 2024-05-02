package com.example.bookstore.data.repository.notification

import com.example.bookstore.base.DataResult
import com.example.bookstore.models.Notify

interface INotificationRepository {
    suspend fun getNotifications(userID: String): DataResult<List<Notify>>
}