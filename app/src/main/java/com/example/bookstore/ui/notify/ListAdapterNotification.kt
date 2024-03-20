package com.example.bookstore.ui.notify

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bookstore.base.BaseAdapter
import com.example.bookstore.base.BaseViewHolder
import com.example.bookstore.databinding.ItemNotifyBinding
import com.example.bookstore.databinding.ItemShopMessageBinding
import com.example.bookstore.models.Evaluate

class ListAdapterNotification : BaseAdapter<Evaluate, BaseViewHolder<Evaluate>>(Evaluate.differUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Evaluate> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNotifyBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(val binding: ItemNotifyBinding) :
        BaseViewHolder<Evaluate>(binding) {
        override fun bindView(item: Evaluate, isItemSelected: Boolean) {
            super.bindView(item, isItemSelected)
            binding.apply {
            }

        }
    }
}