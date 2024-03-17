package com.example.bookstore.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.bookstore.R
import com.example.bookstore.base.BaseAdapter
import com.example.bookstore.base.BaseViewHolder
import com.example.bookstore.databinding.ItemNameTypeBookBinding

class ListAdapterBookTypeName(): BaseAdapter<String,
        BaseViewHolder<String>>(
    object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<String> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNameTypeBookBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }
    inner class ViewHolder( val binding: ItemNameTypeBookBinding) :
        BaseViewHolder<String>(binding) {
        override fun bindView(item: String, isItemSelected: Boolean) {
            super.bindView(item, isItemSelected)

            binding.txtvNameTypeBook.text = item
        }

    }
}