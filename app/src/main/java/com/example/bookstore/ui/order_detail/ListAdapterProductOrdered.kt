package com.example.bookstore.ui.order_detail

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.bookstore.base.BaseAdapter
import com.example.bookstore.base.BaseViewHolder
import com.example.bookstore.databinding.ItemProductOrderedBinding
import com.example.bookstore.models.Book
import com.example.bookstore.models.Cart
import com.example.bookstore.models.Evaluate

class ListAdapterProductOrdered : BaseAdapter<Cart, BaseViewHolder<Cart>>(Cart.differUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Cart> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductOrderedBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(val binding: ItemProductOrderedBinding) :
        BaseViewHolder<Cart>(binding) {
        @SuppressLint("SetTextI18n")
        override fun bindView(item: Cart, isItemSelected: Boolean) {
            super.bindView(item, isItemSelected)
            var sellingPrice =0.0
            binding.apply {
                txtvNameBook.text = item.book.title
                txtvQuantityProductOrder.text = "x" + item.quantity.toString()
                Glide.with(root.rootView)
                    .load(item.book.images[0])
                    .into(imgBook)
                if(item.book.rating != 0.0){
                    txtvPrice.text = decimalFormat.format(item.book.price).toString() +"đ"
                    txtvPrice.paintFlags =  Paint.STRIKE_THRU_TEXT_FLAG
                    sellingPrice = (item.book.price * (100.0-item.book.rating) /100.0)
                }else{
                    txtvPrice.visibility = View.GONE
                    sellingPrice = item.book.price
                }
                txtvPromotionalPrice.text = decimalFormat.format(sellingPrice).toString() + "đ"
            }

        }
    }
}