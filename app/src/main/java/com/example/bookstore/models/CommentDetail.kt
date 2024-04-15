package com.example.bookstore.models

import androidx.recyclerview.widget.DiffUtil
import java.io.Serializable

data class CommentDetail(
    val id: String,
    val ratingID: String,
    val content: String,
    val userID: String,
    val date: String,
    var likes: MutableList<String>,
):Serializable{
    constructor() : this("","","", "", "", mutableListOf())
    companion object{
        val differUtil = object : DiffUtil.ItemCallback<CommentDetail>(){
            override fun areItemsTheSame(oldItem: CommentDetail, newItem: CommentDetail): Boolean =
                oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: CommentDetail, newItem: CommentDetail): Boolean =
                oldItem.id == newItem.id
        }
    }
}
