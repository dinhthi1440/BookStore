package com.example.bookstore.data.repository.book_detail

import com.example.bookstore.base.DataResult
import com.example.bookstore.models.Cart
import com.example.bookstore.models.Comment
import com.example.bookstore.models.CommentDetail
import com.example.bookstore.models.Rating
import com.example.bookstore.models.RatingDetail
import com.example.bookstore.models.TotalRateBook

interface IBookDetailRepository {
    suspend fun addNewCart(cart: Cart): DataResult<Int>
    suspend fun getRating(bookID: String): DataResult<List<RatingDetail>>
    suspend fun addComment(commentDetail: CommentDetail, rating: Rating): DataResult<Int>
    suspend fun addFriends(userID: String, friendID: String): DataResult<Int>
    suspend fun getNumberRateBook(bookID: String): DataResult<TotalRateBook>
    suspend fun deleteRating(rating: Rating): DataResult<Int>
    suspend fun getComment(rating: Rating): DataResult<MutableList<Comment>>
    suspend fun deleteComment(commentDetailID: String, rating: Rating): DataResult<Int>
    suspend fun updateRating(rating: Rating, userLikeID: String, isLike: Boolean): DataResult<Int>
    suspend fun updateComment(commentDetail: CommentDetail, userLikeID: String, isLike: Boolean): DataResult<Int>
}