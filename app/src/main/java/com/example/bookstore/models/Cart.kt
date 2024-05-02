package com.example.bookstore.models

import androidx.recyclerview.widget.DiffUtil
import java.io.Serializable

data class Cart(
    val cardID: String,
    val userID: String,
    val book: Book,
    var quantity: Int,
):Serializable{
    constructor() : this("","", Book(),  0)
    companion object{
        val differUtil = object : DiffUtil.ItemCallback<Cart>(){
            override fun areItemsTheSame(oldItem: Cart, newItem: Cart): Boolean =
                oldItem.cardID == newItem.cardID

            override fun areContentsTheSame(oldItem: Cart, newItem: Cart): Boolean =
                oldItem.cardID == newItem.cardID
        }
    }
}