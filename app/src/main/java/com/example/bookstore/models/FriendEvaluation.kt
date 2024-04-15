package com.example.bookstore.models

import androidx.recyclerview.widget.DiffUtil
import java.io.Serializable

data class FriendEvaluation(
    val id: String,
    val rating: Rating,
    val book: Book,
    val user: User
):Serializable{
    constructor() : this("", Rating(), Book(), User())
    companion object{
        val differUtil = object : DiffUtil.ItemCallback<FriendEvaluation>(){
            override fun areItemsTheSame(oldItem: FriendEvaluation, newItem: FriendEvaluation): Boolean =
                oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: FriendEvaluation, newItem: FriendEvaluation): Boolean =
                oldItem.id == newItem.id
        }
    }
}
