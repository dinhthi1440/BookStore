package com.example.bookstore.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.base.BaseAdapter
import com.example.bookstore.base.BaseViewHolder
import com.example.bookstore.databinding.ItemListTypeBookBinding
import com.example.bookstore.models.TypeBook

class ListAdapterBookType(private val onClick:(String) -> Unit):BaseAdapter<TypeBook, BaseViewHolder<TypeBook>>(TypeBook.differUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<TypeBook> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListTypeBookBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(val binding: ItemListTypeBookBinding) :
        BaseViewHolder<TypeBook>(binding) {
        override fun bindView(item: TypeBook, isItemSelected: Boolean) {
            super.bindView(item, isItemSelected)
            val bookInTypeAdapter = ListAdapterBookIntype(::onClickItemBook)

            binding.apply {
                txtvNameTypeBook.text = item.nameOfTypeBook
                recyclerviewListBook.layoutManager = LinearLayoutManager(root.context, LinearLayoutManager.HORIZONTAL, false)
                bookInTypeAdapter.submitList(item.listBook)
                recyclerviewListBook.adapter = bookInTypeAdapter
                txtvShowMore.setOnClickListener {
                    onClick(txtvNameTypeBook.text.toString())
                }
            }
        }
        private fun onClickItemBook(){
            onClick("")
        }
    }
}