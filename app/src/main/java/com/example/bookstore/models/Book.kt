package com.example.bookstore.models

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Book(
    val id: String,
    val title: String,
    val images: List<String>,
    val price: Double,
    val publisher: String,
    val author: String,
    val genres: List<String>,
    val description: String,
    val rating: Double,
    val promotionPrice: Double

): Serializable{
    companion object{
        val differUtil = object : DiffUtil.ItemCallback<Book>(){
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean =
                oldItem.id == newItem.id
        }
    }
    constructor() : this("", "", emptyList(), 0.0, "", "", emptyList(), "", 0.0, 0.0)
}
