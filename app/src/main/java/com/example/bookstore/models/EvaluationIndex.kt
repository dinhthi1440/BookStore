package com.example.bookstore.models

import androidx.recyclerview.widget.DiffUtil
import java.io.Serializable

data class EvaluationIndex(
    val id: String,
    val bookID: String,
    var star1: Long,
    var star2: Long,
    var star3: Long,
    var star4: Long,
    var star5: Long,
    var averageIndex: Double,
    var totalRating: Long
):Serializable{
    constructor() : this("", "", 0,0,0,0,0,0.0,0)
    companion object{
        val differUtil = object : DiffUtil.ItemCallback<EvaluationIndex>(){
            override fun areItemsTheSame(oldItem: EvaluationIndex, newItem: EvaluationIndex): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: EvaluationIndex, newItem: EvaluationIndex): Boolean =
                oldItem.id == newItem.id
        }
    }
}
