package com.example.bookstore.ui.home

import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.bookstore.base.BaseAdapter
import com.example.bookstore.base.BaseViewHolder
import com.example.bookstore.databinding.ItemABookBinding
import com.example.bookstore.models.Book
import com.example.bookstore.untils.Constant
import com.google.firebase.storage.FirebaseStorage

class ListAdapterBookIntype(private val onClickItemBook: (Book) -> Unit): BaseAdapter<Book, BaseViewHolder<Book>>(Book.differUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Book> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemABookBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
    inner class ViewHolder(val binding: ItemABookBinding) :
        BaseViewHolder<Book>(binding) {
        override fun bindView(item: Book, isItemSelected: Boolean) {
            super.bindView(item, isItemSelected)
            var sellingPrice =0.0
            binding.apply {
                Glide.with(root.rootView)
                    .load(item.images[0])
                    .into(imgBook)
                txtvNameBook.text = item.title
                if(item.rating != 0.0){
                    txtvPromotionPercent.text = "-" + item.rating.toString() + "%"
                    txtvPrice.text = item.price.toString() +"đ"
                    txtvPrice.paintFlags =  Paint.STRIKE_THRU_TEXT_FLAG
                    sellingPrice = (item.price * (100.0-item.rating) /100.0)
                }else{
                    txtvPromotionPercent.visibility = View.INVISIBLE
                    txtvPrice.visibility = View.INVISIBLE
                    sellingPrice = item.price
                }
                txtvSellingPrice.text = sellingPrice.toString() + "đ"
                txtvPrice.paintFlags =  Paint.STRIKE_THRU_TEXT_FLAG
                root.setOnClickListener {
                    onClickItemBook(item)
                }
            }
        }
    }
}