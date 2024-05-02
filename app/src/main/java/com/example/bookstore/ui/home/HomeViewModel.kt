package com.example.bookstore.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.data.repository.home.IHomeRepository
import com.example.bookstore.models.Book
import com.example.bookstore.models.BookGenres
import com.example.bookstore.models.User

class HomeViewModel(private val homeRepository: IHomeRepository):BaseViewModel() {
    private val _getResults = MutableLiveData<List<Book>>()
    val getResults: LiveData<List<Book>> get() = _getResults

    private val _getBookByGenre = MutableLiveData<List<BookGenres>>()
    val getBookByGenre: LiveData<List<BookGenres>> get() = _getBookByGenre

    private val _addFollowResult = MutableLiveData<Int>()
    val addFollowResult: LiveData<Int> get() = _addFollowResult

    private val _getUserResult = MutableLiveData<User>()
    val getUserResult: LiveData<User> get() = _getUserResult

    fun getAllBook(){
        executeTask(
            request = {homeRepository.getAllBook()},
            onSuccess = {
                _getResults.value = it
            },
            onError = {}
        )
    }
    fun getUserByCusID(customerID: String){
        executeTask(
            request = {homeRepository.getUserByCusID(customerID)},
            onSuccess = {_getUserResult.value = it},
            onError = {}
        )
    }
    fun getBookByGenre(){
        executeTask(
            request = {homeRepository.getBookByGenre()},
            onSuccess = {
                _getBookByGenre.value = it
            },
            onError = {}
        )
    }
    fun pushGenreBook(){
        executeTask(
            request = {homeRepository.pushGenreBook()},
            onSuccess = {

            },
            onError = {}
        )
    }
    fun addFriends(userID: String, friendID: String){
        executeTask(
            request = {homeRepository.addFriends(userID, friendID)},
            onSuccess = {_addFollowResult.value = it},
            onError = {}
        )
    }
}