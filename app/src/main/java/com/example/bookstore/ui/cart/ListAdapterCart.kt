package com.example.bookstore.ui.cart

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import com.example.bookstore.R
import com.example.bookstore.base.BaseAdapter
import com.example.bookstore.base.BaseViewHolder
import com.example.bookstore.databinding.ItemBookEvaluateBinding
import com.example.bookstore.databinding.ItemCartBinding
import com.example.bookstore.models.Evaluate

class ListAdapterCart: BaseAdapter<Evaluate, BaseViewHolder<Evaluate>>(Evaluate.differUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Evaluate> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCartBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(val binding: ItemCartBinding) :
        BaseViewHolder<Evaluate>(binding) {
        @SuppressLint("SetTextI18n")
        override fun bindView(item: Evaluate, isItemSelected: Boolean) {
            super.bindView(item, isItemSelected)
            binding.apply {
                radioButton.setOnClickListener{
                    it.isSelected = !it.isSelected
                }
            }
        }
    }
}