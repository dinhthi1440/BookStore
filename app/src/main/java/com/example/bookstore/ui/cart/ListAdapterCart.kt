package com.example.bookstore.ui.cart

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.bookstore.R
import com.example.bookstore.base.BaseAdapter
import com.example.bookstore.base.BaseViewHolder
import com.example.bookstore.databinding.ItemBookEvaluateBinding
import com.example.bookstore.databinding.ItemCartBinding
import com.example.bookstore.models.Book
import com.example.bookstore.models.Cart
import com.example.bookstore.models.Evaluate

class ListAdapterCart(private val onUpdateCarts: (Cart, String) -> Unit)
    : BaseAdapter<Cart, BaseViewHolder<Cart>>(Cart.differUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Cart> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCartBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(val binding: ItemCartBinding) :
        BaseViewHolder<Cart>(binding) {
        @SuppressLint("SetTextI18n")
        override fun bindView(item: Cart, isItemSelected: Boolean) {
            super.bindView(item, isItemSelected)
            var sellingPrice =0.0
            binding.apply {
                txtvQuantity.text = item.quantity.toString()
                txtvNameBook.text = item.book.title
                Glide.with(root.rootView).load(item.book.images[0]).into(imgBook)
                if(item.book.rating != 0.0){
                    txtvPromotionPercent.text = "-" + decimalFormat.format(item.book.rating).toString() + "%"
                    txtvPrice.text = decimalFormat.format(item.book.price).toString() +"đ"
                    txtvPrice.paintFlags =  Paint.STRIKE_THRU_TEXT_FLAG
                    sellingPrice = (item.book.price * (100.0-item.book.rating) /100.0)
                }else{
                    txtvPromotionPercent.visibility = View.GONE
                    txtvPrice.visibility = View.GONE
                    sellingPrice = item.book.price
                }
                txtvSellingPrice.text = decimalFormat.format(sellingPrice).toString() + "đ"
                var quantity = txtvQuantity.text.toString().toInt()
                btnMinus.setOnClickListener {
                    if(quantity > 1){
                        quantity -= 1
                        txtvQuantity.text = quantity.toString()
                        if(checkbox.isChecked){
                            onUpdateCarts(Cart(item.cardID, item.userID, item.book, quantity), "plusAndMinus")
                        }
                    }
                }
                btnPlus.setOnClickListener {
                    quantity += 1
                    txtvQuantity.text = quantity.toString()
                    if(checkbox.isChecked){
                        onUpdateCarts(Cart(item.cardID, item.userID, item.book, quantity), "plusAndMinus")
                    }
                }
                checkbox.setOnCheckedChangeListener { _, isChecked ->
                    if(isChecked){
                        onUpdateCarts(Cart(item.cardID, item.userID, item.book, quantity), "addSelect")
                    }else{
                        onUpdateCarts(Cart(item.cardID, item.userID, item.book, quantity), "removeSelect")
                    }
                }
            }
        }
    }
}