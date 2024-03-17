package com.example.bookstore.models

import androidx.recyclerview.widget.DiffUtil

data class TypeBook(
    val idOfTypeBook: Int,
    val nameOfTypeBook: String,
    val listBook: List<Book>
){
    companion object{
        val differUtil = object : DiffUtil.ItemCallback<TypeBook>(){
            override fun areItemsTheSame(oldItem: TypeBook, newItem: TypeBook): Boolean =
                oldItem.idOfTypeBook == newItem.idOfTypeBook

            override fun areContentsTheSame(oldItem: TypeBook, newItem: TypeBook): Boolean =
                oldItem.idOfTypeBook == newItem.idOfTypeBook
        }
    }
}
