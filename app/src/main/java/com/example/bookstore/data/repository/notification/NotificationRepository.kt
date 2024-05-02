package com.example.bookstore.data.repository.notification

import com.example.bookstore.base.BaseReponsitory
import com.example.bookstore.base.DataResult
import com.example.bookstore.data.service.IFirebaseSource
import com.example.bookstore.models.Notify

class NotificationRepository(private val cloud: IFirebaseSource.CloudStore): BaseReponsitory(),INotificationRepository {
    override suspend fun getNotifications(userID: String): DataResult<List<Notify>> {
        return getResult { cloud.getNotifications(userID) }
    }
}