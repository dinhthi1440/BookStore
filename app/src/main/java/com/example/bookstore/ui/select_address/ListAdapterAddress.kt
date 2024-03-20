package com.example.bookstore.ui.select_address

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bookstore.base.BaseAdapter
import com.example.bookstore.base.BaseViewHolder
import com.example.bookstore.databinding.ItemAddressBinding
import com.example.bookstore.databinding.ItemOrderPayBinding
import com.example.bookstore.models.Evaluate

class ListAdapterAddress: BaseAdapter<Evaluate, BaseViewHolder<Evaluate>>(Evaluate.differUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Evaluate> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAddressBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(val binding: ItemAddressBinding) :
        BaseViewHolder<Evaluate>(binding) {
        override fun bindView(item: Evaluate, isItemSelected: Boolean) {
            super.bindView(item, isItemSelected)
            binding.apply {

            }

        }
    }
}