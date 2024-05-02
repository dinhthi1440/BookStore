package com.example.bookstore.data.repository.chatting

import androidx.lifecycle.LiveData
import com.example.bookstore.base.BaseReponsitory
import com.example.bookstore.base.DataResult
import com.example.bookstore.data.service.IFirebaseSource
import com.example.bookstore.models.Messages

class ChattingRepository(private val cloud: IFirebaseSource.CloudStore): BaseReponsitory(), IChattingRepository {
    override suspend fun addMessage(
        sender: String,
        receiver: String,
        message: String,
        friendImage: String,
        friendName: String,
        userName: String
    ): DataResult<Int> {
        return getResult { cloud.addMessage(sender, receiver,  message, friendImage, friendName, userName) }
    }


}