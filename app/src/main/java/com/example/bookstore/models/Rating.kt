package com.example.bookstore.models

import androidx.recyclerview.widget.DiffUtil
import java.io.Serializable

data class Rating(
    val id: String,
    val bookID: String,
    val customerID: String,
    val content: String,
    val numberRating: Int,
    val date: String,
    var likes: MutableList<String>,
    var comments: MutableList<String>
):Serializable{
    constructor() : this("","","", "", 0, "", mutableListOf(),
        mutableListOf()
    )
    companion object{
        val differUtil = object : DiffUtil.ItemCallback<Rating>(){
            override fun areItemsTheSame(oldItem: Rating, newItem: Rating): Boolean =
                oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Rating, newItem: Rating): Boolean =
                oldItem.id == newItem.id
        }
    }
}
