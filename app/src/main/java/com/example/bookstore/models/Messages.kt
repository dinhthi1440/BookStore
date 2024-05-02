package com.example.bookstore.models

import androidx.recyclerview.widget.DiffUtil
import java.io.Serializable

data class Messages(
    val sender: String,
    val receiver: String,
    val message: String,
    val time: String,
    val date: String
):Serializable{
    constructor(): this("", "", "", "", "")
    val id: String = "$sender-$receiver-$message-$time-$date"
    companion object{
        val differUtil = object : DiffUtil.ItemCallback<Messages>(){
            override fun areItemsTheSame(oldItem: Messages, newItem: Messages): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Messages, newItem: Messages): Boolean =
                oldItem.id == newItem.id
        }
    }
}
