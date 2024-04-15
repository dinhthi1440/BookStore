package com.example.bookstore.models

import androidx.recyclerview.widget.DiffUtil
import java.io.Serializable

data class TotalRateBook(
    val id: String,
    val bookID: String,
    var rate1Star: Int,
    var rate2Star: Int,
    var rate3Star: Int,
    var rate4Star: Int,
    var rate5Star: Int,
):Serializable{
    constructor() : this("","",  0, 0,0,0,0)
    companion object{
        val differUtil = object : DiffUtil.ItemCallback<TotalRateBook>(){
            override fun areItemsTheSame(oldItem: TotalRateBook, newItem: TotalRateBook): Boolean =
                oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: TotalRateBook, newItem: TotalRateBook): Boolean =
                oldItem.id == newItem.id
        }
    }
}
