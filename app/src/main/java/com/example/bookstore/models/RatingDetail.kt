package com.example.bookstore.models

import androidx.recyclerview.widget.DiffUtil
import java.io.Serializable

data class RatingDetail(
    val id: String,
    val user: User,
    val rating: Rating
):Serializable{
    constructor() : this("", User(), Rating())
    companion object{
        val differUtil = object : DiffUtil.ItemCallback<RatingDetail>(){
            override fun areItemsTheSame(oldItem: RatingDetail, newItem: RatingDetail): Boolean =
                oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: RatingDetail, newItem: RatingDetail): Boolean =
                oldItem.id == newItem.id
        }
    }
}
