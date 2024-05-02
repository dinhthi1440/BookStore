package com.example.bookstore.ui.chatting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.base.DataResult
import com.example.bookstore.data.repository.chatting.ChattingRepository
import com.example.bookstore.data.repository.chatting.IChattingRepository
import com.example.bookstore.data.repository.friends.IFriendRepository
import com.example.bookstore.models.Messages
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class ChattingViewModel(private val iChattingRepository: IChattingRepository):BaseViewModel() {

    fun addMessage(sender: String, receiver: String, message: String,
                           friendImage: String, friendName: String,userName: String){
        executeTask(
            request = {iChattingRepository.addMessage(sender, receiver, message, friendImage, friendName, userName)},
            onSuccess = {},
            onError = {}
        )
    }
    fun getMessages(sender: String, receiver: String): LiveData<List<Messages>> {
        val getMessages = MutableLiveData<List<Messages>>()
        val uniqueID = listOf(sender, receiver).sorted()
        val documentRef = Firebase.firestore.collection("Messages")
            .document(uniqueID.toString()).collection("chats")
            .orderBy("date", Query.Direction.ASCENDING)
        documentRef.addSnapshotListener { value, error ->
            if(error != null){
                return@addSnapshotListener
            }
            val messageList = mutableListOf<Messages>()
            if(!value!!.isEmpty){
                value.documents.forEach{ doc ->
                    val messageModel = doc.toObject<Messages>()
                    if (messageModel?.sender.equals(sender) && messageModel?.receiver.equals(receiver) ||
                        messageModel?.sender.equals(receiver) && messageModel?.receiver.equals(sender)) {
                        messageModel.let {
                            messageList.add(it!!)
                        }
                    }
                }
                getMessages.postValue(messageList) // Sử dụng postValue để cập nhật LiveData
            }
        }
        return getMessages
        }
}