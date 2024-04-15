package com.example.bookstore.models

import androidx.recyclerview.widget.DiffUtil
import java.io.Serializable

data class User(
    val userID: String,
    val customerCode: String,
    var userName: String,
    var gender: String,
    var dateOfBirth: String,
    var email: String,
    var phoneNumber: String,
    var imageUser: String,
): Serializable{
    constructor() : this("","","", "", "", "",
        "", ""
    )
    companion object{
        val differUtil = object : DiffUtil.ItemCallback<User>(){
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem.userID == newItem.userID

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem.userID == newItem.userID
        }
    }
}
