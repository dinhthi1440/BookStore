package com.example.bookstore.models

import androidx.recyclerview.widget.DiffUtil
import java.io.Serializable

data class Comment(
    val id: String,
    val user: User,
    val commentDetail: CommentDetail
):Serializable{
    constructor() : this("", User(), CommentDetail()
    )
    companion object{
        val differUtil = object : DiffUtil.ItemCallback<Comment>(){
            override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean =
                oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean =
                oldItem.id == newItem.id
        }
    }
}
