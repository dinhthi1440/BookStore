package com.example.bookstore.data.repository.friends

import com.example.bookstore.base.BaseReponsitory
import com.example.bookstore.base.DataResult
import com.example.bookstore.data.service.IFirebaseSource
import com.example.bookstore.models.FriendEvaluation
import com.example.bookstore.models.Rating

class FriendRepository(private val cloud: IFirebaseSource.CloudStore): BaseReponsitory(), IFriendRepository {
    override suspend fun getFriendEvaluation(userID: String): DataResult<MutableList<FriendEvaluation>> {
        return getResult { cloud.getFriendEvaluation(userID) }
    }

    override suspend fun updateRating(
        rating: Rating,
        userLikeID: String,
        isLike: Boolean
    ): DataResult<Int> {
        return getResult { cloud.updateRating(rating, userLikeID, isLike) }
    }
}