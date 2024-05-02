package com.example.bookstore.ui.pay

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.bookstore.base.BaseAdapter
import com.example.bookstore.base.BaseViewHolder
import com.example.bookstore.databinding.ItemOrderPayBinding
import com.example.bookstore.databinding.ItemProductOrderedBinding
import com.example.bookstore.models.Cart
import com.example.bookstore.models.Evaluate

class ListAdapterBookPay(private val onUpdatePayment: (Cart) -> Unit): BaseAdapter<Cart,
        BaseViewHolder<Cart>>(Cart.differUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Cart> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemOrderPayBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
    inner class ViewHolder(val binding: ItemOrderPayBinding) :
        BaseViewHolder<Cart>(binding) {
        @SuppressLint("SetTextI18n")
        override fun bindView(item: Cart, isItemSelected: Boolean) {
            super.bindView(item, isItemSelected)
            binding.apply {
                var sellingPrice =0.0
                binding.apply {
                    txtvQuantity.text = item.quantity.toString()
                    txtvNameBook.text = item.book.title
                    Glide.with(root.rootView).load(item.book.images[0]).into(imgBook)
                    if(item.book.rating != 0.0){
                        txtvPromotionPercent.text = "-" + item.book.rating.toString() + "%"
                        txtvPrice.text = item.book.price.toString() +"đ"
                        txtvPrice.paintFlags =  Paint.STRIKE_THRU_TEXT_FLAG
                        sellingPrice = (item.book.price * (100.0-item.book.rating) /100.0)
                    }else{
                        txtvPromotionPercent.visibility = View.GONE
                        txtvPrice.visibility = View.GONE
                        sellingPrice = item.book.price
                    }
                    txtvSellingPrice.text = sellingPrice.toString() + "đ"
                    var quantity = txtvQuantity.text.toString().toInt()
                    btnMinus.setOnClickListener {
                        if(quantity > 1){
                            quantity -= 1
                            txtvQuantity.text = quantity.toString()
                            onUpdatePayment(Cart(item.cardID, item.userID, item.book, quantity))
                        }
                    }
                    btnPlus.setOnClickListener {
                        quantity += 1
                        txtvQuantity.text = quantity.toString()
                        onUpdatePayment(Cart(item.cardID, item.userID, item.book, quantity))
                    }
            } }

        }
    }
}