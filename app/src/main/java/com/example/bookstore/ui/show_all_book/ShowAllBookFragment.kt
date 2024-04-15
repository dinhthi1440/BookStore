package com.example.bookstore.ui.show_all_book

import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.R
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.databinding.FragmentShowAllBookBinding
import com.example.bookstore.models.Book
import com.example.bookstore.models.BookGenres
import com.example.bookstore.models.Evaluate
import com.example.bookstore.ui.home.ListAdapterBookIntype
import com.example.bookstore.ui.order_detail.ListAdapterProductOrdered


class ShowAllBookFragment :BaseFragment<FragmentShowAllBookBinding>(FragmentShowAllBookBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[ShowAllBookViewModel::class.java]

    override fun initData() {

    }

    override fun handleEvent() {
        binding.apply {
            imgBack.setOnClickListener { findNavController().popBackStack() }
        }
    }

    override fun bindData() {

        binding.apply {
            val bookGenres: BookGenres? = arguments?.getSerializable("bookGenre") as? BookGenres
            txtvBookCatogory.text = bookGenres?.nameGenre
            val listAdapterAllBook = ListAdapterBookIntype(::bookOnClick)
            recyclerviewListBook.layoutManager = GridLayoutManager(root.context, 2)
            listAdapterAllBook.submitList(bookGenres?.listBook)
            recyclerviewListBook.adapter = listAdapterAllBook
        }
    }
    private fun bookOnClick(book: Book){
        val bundle = bundleOf("bookDetail" to book)
        findNavController().navigate(R.id.action_showAllBookFragment_to_bookDetailFragment, bundle )
    }

    override fun destroy() {

    }
}