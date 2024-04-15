package com.example.bookstore.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.base.BaseAdapter
import com.example.bookstore.base.BaseViewHolder
import com.example.bookstore.databinding.ItemListTypeBookBinding
import com.example.bookstore.models.Book
import com.example.bookstore.models.BookGenres

class ListAdapterBookType(private val onClick:(Boolean, BookGenres, Book) -> Unit):BaseAdapter<BookGenres, BaseViewHolder<BookGenres>>(BookGenres.differUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BookGenres> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListTypeBookBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(val binding: ItemListTypeBookBinding) :
        BaseViewHolder<BookGenres>(binding) {
        override fun bindView(item: BookGenres, isItemSelected: Boolean) {
            super.bindView(item, isItemSelected)
            val bookInTypeAdapter = ListAdapterBookIntype(::onClickItemBook)

            binding.apply {
                txtvNameTypeBook.text = item.nameGenre
                recyclerviewListBook.layoutManager = LinearLayoutManager(root.context, LinearLayoutManager.HORIZONTAL, false)
                bookInTypeAdapter.submitList(item.listBook)
                recyclerviewListBook.adapter = bookInTypeAdapter
                txtvShowMore.setOnClickListener {
                    onClick(true, item, Book())
                }
            }
        }
        private fun onClickItemBook(book: Book){
            onClick(false, BookGenres(), book)
        }
    }
}