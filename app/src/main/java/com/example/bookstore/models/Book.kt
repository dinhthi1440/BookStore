package com.example.bookstore.models

import androidx.recyclerview.widget.DiffUtil

data class Book(
    val idOfBook: Int,
    val nameOfBook: String,
//    val imgOfBook: String,
//    val priceOfBook: Double,
//    val publisherOfBook: String,
//    val authorOfBook: String,
    val bookCategory: String
){
    companion object{
        val differUtil = object : DiffUtil.ItemCallback<Book>(){
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean =
                oldItem.idOfBook == newItem.idOfBook

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean =
                oldItem.idOfBook == newItem.idOfBook
        }
    }
}
