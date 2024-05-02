package com.example.bookstore.models

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class District(
    val name: String,
    val type: String,
    val slug: String,
    @SerializedName("name_with_type")
    val nameWithType: String,
    val path: String,
    @SerializedName("path_with_type")
    val pathWithType: String,
    val code: String,
    @SerializedName("parent_code")
    val parentCode: String
): Serializable{
    constructor() : this("", "","","","","","", "")
    companion object{
        val differUtil = object : DiffUtil.ItemCallback<District>(){
            override fun areItemsTheSame(oldItem: District, newItem: District): Boolean =
                oldItem.code == newItem.code

            override fun areContentsTheSame(oldItem: District, newItem: District): Boolean =
                oldItem.code == newItem.code
        }
    }
}
