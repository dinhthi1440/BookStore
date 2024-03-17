package com.example.bookstore.models

import androidx.recyclerview.widget.DiffUtil

data class Evaluate(
    val idEvaluate: String,
    val idUser: Int,
//    val userName: String,
//    val timePost: String,
//    val stars: Int,
//    val numberLike: Int,
    val content: String
){
    companion object{
        val differUtil = object : DiffUtil.ItemCallback<Evaluate>(){
            override fun areItemsTheSame(oldItem: Evaluate, newItem: Evaluate): Boolean =
                oldItem.idEvaluate == newItem.idEvaluate
            override fun areContentsTheSame(oldItem: Evaluate, newItem: Evaluate): Boolean =
                oldItem.idEvaluate == newItem.idEvaluate
        }
    }
}
