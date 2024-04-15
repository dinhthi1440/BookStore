package com.example.bookstore.ui.my_friends

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.data.repository.my_friends.IMyFriendRepository
import com.example.bookstore.models.User

class ListFriendViewModel(private val iMyFriendRepository: IMyFriendRepository):BaseViewModel() {
    private val _getListFriendResult = MutableLiveData<MutableList<User>>()
    val getListFriendResult: LiveData<MutableList<User>> get() = _getListFriendResult
    fun getListFriend(userID: String){
        executeTask(
            request = {iMyFriendRepository.getListFriend(userID)},
            onSuccess = {_getListFriendResult.value = it},
            onError = {}
        )
    }

    private val _getDeleFriendResult = MutableLiveData<Int>()
    val getDeleFriendResult: LiveData<Int> get() = _getDeleFriendResult
    fun deleteFriends(userID: String, friendID: String){
        executeTask(
            request = {iMyFriendRepository.deleteFriends(userID, friendID)},
            onSuccess = {_getDeleFriendResult.value = it},
            onError = {}
        )
    }
}