package com.example.bookstore.ui.book_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.data.repository.book_detail.IBookDetailRepository
import com.example.bookstore.models.Cart
import com.example.bookstore.models.Comment
import com.example.bookstore.models.CommentDetail
import com.example.bookstore.models.Rating
import com.example.bookstore.models.RatingDetail
import com.example.bookstore.models.TotalRateBook

class BookDetailViewModel(private val iBookDetailRepository: IBookDetailRepository):BaseViewModel() {
    private val _getResultAddCart = MutableLiveData<Int>()
    val getResultAddCart: LiveData<Int> get() = _getResultAddCart

    private val _getResultAddComment = MutableLiveData<Int>()
    val getResultAddComment: LiveData<Int> get() = _getResultAddComment

    private val _getListRating = MutableLiveData<List<RatingDetail>>()
    val getListRating: LiveData<List<RatingDetail>> get() = _getListRating
    private val _getAddFriendResult = MutableLiveData<Int>()
    val getAddFriendResult: LiveData<Int> get() = _getAddFriendResult
    private val _getRatingNumResult = MutableLiveData<TotalRateBook>()
    val getRatingNumResult: LiveData<TotalRateBook> get() = _getRatingNumResult

    private val _getDeleteRating = MutableLiveData<Int>()
    val getDeleteRating: LiveData<Int> get() = _getDeleteRating

    private val _getCommentResult = MutableLiveData<MutableList<Comment>>()
    val getCommentResult: LiveData<MutableList<Comment>> get() = _getCommentResult
    private val _getDeleCommentResult = MutableLiveData<Int>()
    val getDeleCommentResult: LiveData<Int> get() = _getDeleCommentResult
    private val _getUpdateRatingResult = MutableLiveData<Int>()
    val getUpdateRatingResult: LiveData<Int> get() = _getUpdateRatingResult
    private val _getUpdateCommentResult = MutableLiveData<Int>()
    val getUpdateCommentResult: LiveData<Int> get() = _getUpdateCommentResult
    fun addNewCart(cart: Cart){
        executeTask(
            request = {iBookDetailRepository.addNewCart(cart)},
            onSuccess = {
                _getResultAddCart.value = it
            },
            onError = {}
        )
    }

    fun getRating(bookID: String){
        executeTask(
            request = {iBookDetailRepository.getRating(bookID)},
            onSuccess = {_getListRating.value = it},
            onError = {}
        )
    }
    fun addComment(commentDetail: CommentDetail, rating: Rating){
        executeTask(
            request = {iBookDetailRepository.addComment(commentDetail, rating)},
            onSuccess = {_getResultAddComment.value = it},
            onError = {}
        )
    }
    fun addFriends(userID: String, friendID: String){
        executeTask(
            request = {iBookDetailRepository.addFriends(userID, friendID)},
            onSuccess = {_getAddFriendResult.value = it},
            onError = {}
        )
    }
    fun getNumberRateBook(bookID: String){
        executeTask(
            request = {iBookDetailRepository.getNumberRateBook(bookID)},
            onSuccess = {_getRatingNumResult.value = it},
            onError = {}
        )
    }
    fun deleteRating(rating: Rating){
        executeTask(
            request = {iBookDetailRepository.deleteRating(rating)},
            onSuccess = {_getDeleteRating.value = it},
            onError = {}
        )
    }
    fun getComment(rating: Rating){
        executeTask(
            request = {iBookDetailRepository.getComment(rating)},
            onSuccess = {_getCommentResult.value = it},
            onError = {}
        )
    }

    fun deleteComment(commentDetailID: String, rating: Rating){
        executeTask(
            request = {iBookDetailRepository.deleteComment(commentDetailID, rating)},
            onSuccess = {_getDeleCommentResult.value = it},
            onError = {}
        )
    }
    fun updateRating(
        rating: Rating,
        userLikeID: String,
        isLike: Boolean
    ){
        executeTask(
            request = {iBookDetailRepository.updateRating(rating, userLikeID, isLike)},
            onSuccess = {_getUpdateRatingResult.value = it},
            onError = {}
        )
    }
    fun updateComment(commentDetail: CommentDetail, userLikeID: String, isLike: Boolean){
        executeTask(
            request = {iBookDetailRepository.updateComment(commentDetail, userLikeID, isLike)},
            onSuccess = {_getUpdateCommentResult.value = it},
            onError = {}
        )
    }
}