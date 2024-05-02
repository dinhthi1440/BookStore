package com.example.bookstore.models

import androidx.recyclerview.widget.DiffUtil
import java.io.Serializable

data class Address(
    val id: String,
    var name: String,
    var phoneNumber: String,
    var province: String,
    var district: String,
    var communeOrAward: String,
    var detailDescription: String,
    var coordinates: String,
    var isDefault: Boolean
):Serializable{
    constructor(): this("", "", "", "", "","","",  "", false)
    companion object{
        val differUtil = object : DiffUtil.ItemCallback<Address>(){
            override fun areItemsTheSame(oldItem: Address, newItem: Address): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Address, newItem: Address): Boolean =
                oldItem.id == newItem.id
        }
    }
}