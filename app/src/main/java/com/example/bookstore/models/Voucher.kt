package com.example.bookstore.models

import androidx.recyclerview.widget.DiffUtil
import java.io.Serializable

data class Voucher(
    val voucherID: String,
    val voucherType: String,
    val description: String,
    val expirationDate: String,
    val icon: String,
    val voucherPercent: Int,
    val minPrice: Double,
    val maxDiscount: Double
): Serializable {
    constructor() : this("","", "","", "", 0, 0.0, 0.0)
    companion object{
        val differUtil = object : DiffUtil.ItemCallback<Voucher>(){
            override fun areItemsTheSame(oldItem: Voucher, newItem: Voucher): Boolean =
                oldItem.voucherID == newItem.voucherID

            override fun areContentsTheSame(oldItem: Voucher, newItem: Voucher): Boolean =
                oldItem.voucherID == newItem.voucherID
        }
    }
}