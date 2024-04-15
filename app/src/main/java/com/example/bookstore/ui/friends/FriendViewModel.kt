package com.example.bookstore.ui.friends

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.data.repository.friends.IFriendRepository
import com.example.bookstore.models.FriendEvaluation
import com.example.bookstore.models.Rating

class FriendViewModel(private val iFriendRepository: IFriendRepository):BaseViewModel() {
    private val _getFriendEvalResult = MutableLiveData<MutableList<FriendEvaluation>>()
    val getFriendEvalResult: LiveData<MutableList<FriendEvaluation>> get() = _getFriendEvalResult

    private val _getUpdateRatingResult = MutableLiveData<Int>()
    val getUpdateRatingResult: LiveData<Int> get() = _getUpdateRatingResult
     fun getFriendEvaluation(userID: String){
         executeTask(
             request = {iFriendRepository.getFriendEvaluation(userID)},
             onSuccess = {_getFriendEvalResult.value = it},
             onError = {}
         )
     }
    fun updateRating(
        rating: Rating,
        userLikeID: String,
        isLike: Boolean
    ){
        executeTask(
            request = {iFriendRepository.updateRating(rating, userLikeID, isLike)},
            onSuccess = {_getUpdateRatingResult.value = it},
            onError = {}
        )
    }
}