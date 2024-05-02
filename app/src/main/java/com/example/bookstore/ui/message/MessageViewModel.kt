package com.example.bookstore.ui.message

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.data.repository.my_friends.IMyFriendRepository
import com.example.bookstore.models.Order
import com.example.bookstore.models.RecentChats
import com.example.bookstore.models.User
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MessageViewModel(private val iMyFriendRepository: IMyFriendRepository): BaseViewModel() {

    private val _getListUserResult = MutableLiveData<List<User>>()
    val getListUserResult: LiveData<List<User>> get() = _getListUserResult
    fun getListFriend(userID: String){
        executeTask(
            request = {iMyFriendRepository.getListFriend(userID)},
            onSuccess = {_getListUserResult.value = it},
            onError = {}
        )
    }

    fun getAllChatList(userID: String): LiveData<List<RecentChats>> {

        val mainChatList = MutableLiveData<List<RecentChats>>()

        Firebase.firestore.collection("MessagesForRecent").document("ByUserID")
            .collection(userID).orderBy("time", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, exception ->
                if (exception != null) {
                    return@addSnapshotListener
                }
                val chatlist = mutableListOf<RecentChats>()
                snapshot?.forEach { document ->
                    val chatlistmodel = document.toObject(RecentChats::class.java)
                    if (chatlistmodel.sender.equals(userID)) {
                        chatlistmodel.let {
                            chatlist.add(it)
                        }
                    }
                }
                mainChatList.value = chatlist
            }
        return mainChatList
    }
}