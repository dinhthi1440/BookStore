package com.example.bookstore.ui.rating

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.data.repository.rating.IRatingRepository
import com.example.bookstore.models.Rating

class RatingViewModel(private val iRatingRepository: IRatingRepository):BaseViewModel() {
    private val _getAddResult = MutableLiveData<Int>()
    val getAddResult: LiveData<Int> get() = _getAddResult
    fun addRating(rating: Rating){
        executeTask(
            request = {iRatingRepository.addRating(rating)},
            onSuccess = {
                _getAddResult.value = it},
            onError = {}
        )
    }

}