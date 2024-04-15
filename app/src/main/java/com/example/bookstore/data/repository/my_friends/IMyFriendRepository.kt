package com.example.bookstore.data.repository.my_friends

import com.example.bookstore.base.DataResult
import com.example.bookstore.models.User

interface IMyFriendRepository {
    suspend fun getListFriend(userID: String): DataResult<MutableList<User>>
    suspend fun deleteFriends(userID: String, friendID: String): DataResult<Int>
}