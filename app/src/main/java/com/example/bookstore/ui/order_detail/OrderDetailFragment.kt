package com.example.bookstore.ui.order_detail

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.databinding.FragmentOrderDetailBinding
import com.example.bookstore.models.Evaluate


class OrderDetailFragment:BaseFragment<FragmentOrderDetailBinding>(FragmentOrderDetailBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[OrderDetailViewModel::class.java]

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
        )
        binding.apply {

            val listAdapterProductOrdered = ListAdapterProductOrdered()
            recyclerViewProductOrdered.layoutManager = LinearLayoutManager(root.context)
            listAdapterProductOrdered.submitList(listEvaluate)
            recyclerViewProductOrdered.adapter = listAdapterProductOrdered

            imgBack.setOnClickListener { findNavController().popBackStack() }
        }
    }

    override fun destroy() {

    }
}