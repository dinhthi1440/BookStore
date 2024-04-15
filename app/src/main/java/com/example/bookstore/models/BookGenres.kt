package com.example.bookstore.models

import androidx.recyclerview.widget.DiffUtil
import java.io.Serializable

data class BookGenres(
    val nameGenre: String,
    val listBook: MutableList<Book>
):Serializable{
    constructor() : this("", mutableListOf())
    companion object{
        val differUtil = object : DiffUtil.ItemCallback<BookGenres>(){
            override fun areItemsTheSame(oldItem: BookGenres, newItem: BookGenres): Boolean =
                oldItem.nameGenre == newItem.nameGenre

            override fun areContentsTheSame(oldItem: BookGenres, newItem: BookGenres): Boolean =
                oldItem.nameGenre == newItem.nameGenre
        }
    }
}
