package com.example.bookstore.ui.show_all_book

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bookstore.base.BaseAdapter
import com.example.bookstore.base.BaseViewHolder
import com.example.bookstore.databinding.ItemABookBinding
import com.example.bookstore.databinding.ItemProductOrderedBinding
import com.example.bookstore.models.Evaluate

class ListAdapterAllBook: BaseAdapter<Evaluate, BaseViewHolder<Evaluate>>(Evaluate.differUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Evaluate> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemABookBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(val binding: ItemABookBinding) :
        BaseViewHolder<Evaluate>(binding) {
        override fun bindView(item: Evaluate, isItemSelected: Boolean) {
            super.bindView(item, isItemSelected)
            binding.apply {

            }

        }
    }
}