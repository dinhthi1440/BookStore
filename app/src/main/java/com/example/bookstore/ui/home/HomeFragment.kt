package com.example.bookstore.ui.home

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.R
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.databinding.FragmentHomeBinding
import com.example.bookstore.models.Book
import com.example.bookstore.models.TypeBook

class HomeFragment :BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[HomeViewModel::class.java]

    private val nameTypeBookAdapter by lazy{
        ListAdapterBookTypeName()
    }
    private val listTypeBookAdapter by lazy{
        ListAdapterBookType()
    }
    override fun initData() {

    }

    override fun handleEvent() {

    }

    override fun bindData() {
        var listName: List<String> = listOf("All", "Trinh thám", "Ngôn tình", "Tiểu thuyết", "Kinh dị")
        var bookTT = TypeBook(1, "Trinh thám", listOf(
            Book(1, "Đắc nhân tâm quyển 1", "Trinh thám"),
            Book(2, "Sách 2", "Trinh thám"),
        ))
        var bookNT = TypeBook(2, "Ngôn tình", listOf(
            Book(3, "Sách 3", "Ngôn tình"),
            Book(4, "Sách 4", "Ngôn tình"),
        ))

        binding.apply {
            recycleviewNameTypeBook.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recycleviewNameTypeBook.adapter = nameTypeBookAdapter
            nameTypeBookAdapter.submitList(listName)

            recyclerviewListTypeBook.layoutManager = LinearLayoutManager(root.context)
            listTypeBookAdapter.submitList(listOf(bookTT, bookNT))
            recyclerviewListTypeBook.adapter = listTypeBookAdapter

            cart.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_bookDetailFragment)
            }
            voucher.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_orderDetailFragment)
            }
            orders.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_orderFragment)
            }
            shopMessage.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_shopMessageFragment)
            }
        }





    }

    override fun destroy() {

    }
}