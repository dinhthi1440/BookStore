package com.example.bookstore.data.repository.chatting

import com.example.bookstore.base.DataResult

interface IChattingRepository {
    suspend fun addMessage(
        sender: String, receiver: String, message: String,
        friendImage: String, friendName: String, userName: String
    ): DataResult<Int>
}