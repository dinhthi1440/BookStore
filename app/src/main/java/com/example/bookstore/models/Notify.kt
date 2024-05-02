package com.example.bookstore.models

import androidx.recyclerview.widget.DiffUtil
import java.io.Serializable

data class Notify(
    val notificationID: String,
    val userID: String,
    val title: String,
    val description: String,
    val time: String,
    val icon: String,
):Serializable{
    constructor(): this("", "", "", "", "", "")
    companion object{
        val differUtil = object : DiffUtil.ItemCallback<Notify>(){
            override fun areItemsTheSame(oldItem: Notify, newItem: Notify): Boolean =
                oldItem.notificationID == newItem.notificationID

            override fun areContentsTheSame(oldItem: Notify, newItem: Notify): Boolean =
                oldItem.notificationID == newItem.notificationID
        }
    }
}