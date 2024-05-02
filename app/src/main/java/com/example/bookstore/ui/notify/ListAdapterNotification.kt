package com.example.bookstore.ui.notify

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bookstore.base.BaseAdapter
import com.example.bookstore.base.BaseViewHolder
import com.example.bookstore.databinding.ItemNotifyBinding
import com.example.bookstore.databinding.ItemShopMessageBinding
import com.example.bookstore.models.Evaluate
import com.example.bookstore.models.Notify

class ListAdapterNotification : BaseAdapter<Notify, BaseViewHolder<Notify>>(Notify.differUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Notify> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNotifyBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(val binding: ItemNotifyBinding) :
        BaseViewHolder<Notify>(binding) {
        override fun bindView(item: Notify, isItemSelected: Boolean) {
            super.bindView(item, isItemSelected)
            binding.apply {
                txtvTimeNotify.text = item.time
                txtvNotifyType.text = item.title
                txtvContentNotification.text = item.description
            }

        }
    }
}