package com.example.bookstore.ui.order

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.databinding.FragmentOrderBinding
import com.example.bookstore.models.Evaluate
import com.example.bookstore.ui.order_detail.ListAdapterProductOrdered

class OrderFragment : BaseFragment<FragmentOrderBinding>(FragmentOrderBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[OrderViewModel::class.java]

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

            val listAdapterOrder = ListAdapterOrder()
            recyclerviewListCart.layoutManager = LinearLayoutManager(root.context)
            listAdapterOrder.submitList(listEvaluate)
            recyclerviewListCart.adapter = listAdapterOrder
        }
    }

    override fun destroy() {
        
    }
}