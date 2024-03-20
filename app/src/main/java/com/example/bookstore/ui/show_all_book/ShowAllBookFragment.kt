package com.example.bookstore.ui.show_all_book

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.databinding.FragmentShowAllBookBinding
import com.example.bookstore.models.Evaluate
import com.example.bookstore.ui.order_detail.ListAdapterProductOrdered


class ShowAllBookFragment :BaseFragment<FragmentShowAllBookBinding>(FragmentShowAllBookBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[ShowAllBookViewModel::class.java]

    override fun initData() {

    }

    override fun handleEvent() {

    }

    override fun bindData() {
        val listEvaluate = listOf(
            Evaluate("1", 1, "Truyện đẹp"),
            Evaluate("2", 1, "Lên top thôi"),
            Evaluate("3", 1, "Truyện hay lắm"),
            Evaluate("4", 1, "Hay quá trời hay"),
            Evaluate("5", 1, "Hay lắm fen"),
            Evaluate("6", 1, "Truyện đẹp"),
            Evaluate("7", 1, "Lên top thôi"),
            Evaluate("8", 1, "Truyện hay lắm"),
            Evaluate("9", 1, "Hay quá trời hay"),
            Evaluate("10", 1, "Hay lắm fen"),
        )

        binding.apply {
            txtvBookCatogory.text = arguments?.getString("bookCategory")
            val listAdapterAllBook = ListAdapterAllBook()
            recyclerviewListBook.layoutManager = GridLayoutManager(root.context, 2)
            listAdapterAllBook.submitList(listEvaluate)
            recyclerviewListBook.adapter = listAdapterAllBook
        }
    }

    override fun destroy() {

    }
}