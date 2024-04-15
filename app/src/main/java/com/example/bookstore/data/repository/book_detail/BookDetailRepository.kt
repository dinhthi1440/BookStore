package com.example.bookstore.data.repository.book_detail

import com.example.bookstore.base.BaseReponsitory
import com.example.bookstore.base.DataResult
import com.example.bookstore.data.service.IFirebaseSource
import com.example.bookstore.models.Cart
import com.example.bookstore.models.Comment
import com.example.bookstore.models.CommentDetail
import com.example.bookstore.models.Rating
import com.example.bookstore.models.RatingDetail
import com.example.bookstore.models.TotalRateBook

class BookDetailRepository(private val cloud: IFirebaseSource.CloudStore): BaseReponsitory(),
    IBookDetailRepository {
    override suspend fun addNewCart(cart: Cart): DataResult<Int> {
        return getResult { cloud.addNewCart(cart) }
    }

    override suspend fun getRating(bookID: String): DataResult<List<RatingDetail>> {
        return getResult { cloud.getRating(bookID) }
    }

    override suspend fun addComment(commentDetail: CommentDetail, rating: Rating): DataResult<Int> {
        return getResult { cloud.addComment(commentDetail,  rating) }
    }

    override suspend fun addFriends(userID: String, friendID: String): DataResult<Int> {
        return getResult { cloud.addFriends(userID, friendID) }
    }

    override suspend fun getNumberRateBook(bookID: String): DataResult<TotalRateBook> {
        return getResult { cloud.getNumberRateBook(bookID) }
    }

    override suspend fun deleteRating(rating: Rating): DataResult<Int> {
        return getResult { cloud.deleteRating(rating) }
    }

    override suspend fun getComment(rating: Rating): DataResult<MutableList<Comment>> {
        return getResult { cloud.getComment(rating) }
    }

    override suspend fun deleteComment(commentDetailID: String, rating: Rating): DataResult<Int> {
        return getResult { cloud.deleteComment(commentDetailID, rating) }
    }

    override suspend fun updateRating(
        rating: Rating,
        userLikeID: String,
        isLike: Boolean
    ): DataResult<Int> {
        return getResult { cloud.updateRating(rating, userLikeID, isLike) }
    }

    override suspend fun updateComment(
        commentDetail: CommentDetail,
        userLikeID: String,
        isLike: Boolean
    ): DataResult<Int> {
        return getResult { cloud.updateComment(commentDetail, userLikeID, isLike) }
    }

}