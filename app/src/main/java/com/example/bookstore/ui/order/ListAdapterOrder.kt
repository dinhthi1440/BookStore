package com.example.bookstore.ui.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.base.BaseAdapter
import com.example.bookstore.base.BaseViewHolder
import com.example.bookstore.databinding.ItemOrderedBinding
import com.example.bookstore.databinding.ItemProductOrderedBinding
import com.example.bookstore.models.Evaluate
import com.example.bookstore.ui.order_detail.ListAdapterProductOrdered

class ListAdapterOrder: BaseAdapter<Evaluate, BaseViewHolder<Evaluate>>(Evaluate.differUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Evaluate> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemOrderedBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(val binding: ItemOrderedBinding) :
        BaseViewHolder<Evaluate>(binding) {
        override fun bindView(item: Evaluate, isItemSelected: Boolean) {
            super.bindView(item, isItemSelected)
            val listEvaluate = listOf(
                Evaluate("1", 1, "Truyện đẹp"),
                Evaluate("2", 1, "Lên top thôi"),
            )
            val listAdapterProductOrdered = ListAdapterProductOrdered()
            binding.apply {
                recyclerviewListItemOrder.layoutManager = LinearLayoutManager(root.context, LinearLayoutManager.HORIZONTAL, false)
                listAdapterProductOrdered.submitList(listEvaluate)
                recyclerviewListItemOrder.adapter = listAdapterProductOrdered
            }
            binding.btnCancelOrder.setOnClickListener {

            }

        }
    }
}