package com.example.bookstore.models

import androidx.recyclerview.widget.DiffUtil
import java.io.Serializable

data class RecentChats(
    var date: String,
    var friendID: String,
    var friendsImage: String,
    var message: String,
    var name: String,
    var person: String,
    var sender: String,
    var time: String,

):Serializable{
    constructor(): this("", "", "", "", "","", "", "")
    val id: String = "$sender-$friendID-$message-$time-$date"
    companion object{
        val differUtil = object : DiffUtil.ItemCallback<RecentChats>(){
            override fun areItemsTheSame(oldItem: RecentChats, newItem: RecentChats): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: RecentChats, newItem: RecentChats): Boolean =
                oldItem.id == newItem.id
        }
    }
}