package com.example.bookstore.models

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Book(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("images")
    val images: List<String>,
    @SerializedName("price")
    val price: Double,
    @SerializedName("publisher")
    val publisher: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("genres")
    val genres: List<String>,
    @SerializedName("description")
    val description: String,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("promotionPrice")
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
