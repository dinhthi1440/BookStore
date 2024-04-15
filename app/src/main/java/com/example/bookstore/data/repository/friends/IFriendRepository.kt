package com.example.bookstore.data.repository.friends

import com.example.bookstore.base.DataResult
import com.example.bookstore.models.FriendEvaluation
import com.example.bookstore.models.Rating

interface IFriendRepository {
    suspend fun getFriendEvaluation(userID: String): DataResult<MutableList<FriendEvaluation>>
    suspend fun updateRating(rating: Rating, userLikeID: String, isLike: Boolean): DataResult<Int>

}