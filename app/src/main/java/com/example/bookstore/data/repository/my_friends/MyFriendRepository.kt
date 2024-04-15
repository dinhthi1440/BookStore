package com.example.bookstore.data.repository.my_friends

import com.example.bookstore.base.BaseReponsitory
import com.example.bookstore.base.DataResult
import com.example.bookstore.data.service.IFirebaseSource
import com.example.bookstore.models.User

class MyFriendRepository(private val cloud: IFirebaseSource.CloudStore): BaseReponsitory(),IMyFriendRepository {
    override suspend fun getListFriend(userID: String): DataResult<MutableList<User>> {
        return getResult { cloud.getListFriend(userID) }
    }

    override suspend fun deleteFriends(userID: String, friendID: String): DataResult<Int> {
        return getResult { cloud.deleteFriends(userID, friendID) }
    }
}