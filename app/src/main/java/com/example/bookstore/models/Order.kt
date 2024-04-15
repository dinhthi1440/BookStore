package com.example.bookstore.models

import androidx.recyclerview.widget.DiffUtil
import java.io.Serializable

data class Order(
    val id: String,
    val customerID: String,
    var orderStatus: String,
    val listCart: List<Cart>,
    val totalPrice: Double,
    val totalTransportFee: Double,
    val totalPayment: Double,
    val voucher: Voucher?,
    val payMethod: String,
    val orderDate: String,
    val shopAddress: String,
    val customerAddress: String,
    val customerPhoneNumber: String,
    val customerName: String,
):Serializable{
    constructor() : this("","","", mutableListOf(), 0.0, 0.0, 0.0,
        Voucher(), "", "", "", "", "", ""
    )
    companion object{
        val differUtil = object : DiffUtil.ItemCallback<Order>(){
            override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean =
                oldItem.id == newItem.id
        }
    }
}
