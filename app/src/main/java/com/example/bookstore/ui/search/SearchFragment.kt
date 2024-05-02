package com.example.bookstore.ui.search

import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bookstore.R
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.databinding.FragmentSearchBinding
import com.example.bookstore.models.Book
import com.example.bookstore.ui.home.HomeViewModel
import com.example.bookstore.ui.home.ListAdapterBookIntype
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment:BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {
    override val viewModel by viewModel<HomeViewModel>()
    private var listBook = listOf<Book>()
    private val listAdapterAllBook by lazy { ListAdapterBookIntype(::bookOnClick)}
    private var nameSearch = ""

    override fun initData() {
        viewModel.getAllBook()
    }

    override fun handleEvent() {
        binding.apply {
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
            txtvSearch.setOnClickListener {
                scroll.visibility = View.VISIBLE
                txtvWarning.visibility = View.GONE
                val listBookSearch =listBook?.filter { book ->
                    book.title?.contains(actvSearch.text.toString(), ignoreCase = true) == true
                }
                listAdapterAllBook.submitList(listBookSearch)
            }

            actvSearch.addTextChangedListener {
                if(actvSearch.text.toString() != ""){
                    imgCancel.visibility = View.VISIBLE
                }else imgCancel.visibility = View.GONE
            }
            imgCancel.setOnClickListener { 
                actvSearch.setText("")
            }
        }
    }

    override fun bindData() {
        binding.recyclerviewListBook.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerviewListBook.adapter = listAdapterAllBook
        viewModel.getResults.observe(viewLifecycleOwner){
            listBook = it
            val listNameBook = it.map { it.title }
            val arrayAdapter = ArrayAdapter<String>(requireContext(), R.layout.item_dropdown, listNameBook)
            binding.actvSearch.setAdapter(arrayAdapter)
        }
    }
    private fun bookOnClick(book: Book){
        val bundle = bundleOf("bookDetail" to book)
        findNavController().navigate(R.id.action_searchFragment_to_bookDetailFragment, bundle )
    }
    override fun destroy() {

    }
}