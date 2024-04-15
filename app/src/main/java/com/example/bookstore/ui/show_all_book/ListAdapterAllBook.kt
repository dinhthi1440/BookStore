package com.example.bookstore.ui.show_all_book

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bookstore.base.BaseAdapter
import com.example.bookstore.base.BaseViewHolder
import com.example.bookstore.databinding.ItemABookBinding
import com.example.bookstore.databinding.ItemProductOrderedBinding
import com.example.bookstore.models.Book
import com.example.bookstore.models.Evaluate

class ListAdapterAllBook: BaseAdapter<Book, BaseViewHolder<Book>>(Book.differUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Book> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemABookBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(val binding: ItemABookBinding) :
        BaseViewHolder<Book>(binding) {
        override fun bindView(item: Book, isItemSelected: Boolean) {
            super.bindView(item, isItemSelected)
            binding.apply {

            }

        }
    }
}