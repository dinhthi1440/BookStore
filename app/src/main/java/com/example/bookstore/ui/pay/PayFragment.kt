package com.example.bookstore.ui.pay

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.R
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.databinding.FragmentPayBinding
import com.example.bookstore.models.Evaluate
import com.example.bookstore.ui.order_detail.ListAdapterProductOrdered

class PayFragment:BaseFragment<FragmentPayBinding>(FragmentPayBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[PayViewModel::class.java]

    override fun initData() {

    }

    override fun handleEvent() {
        binding.apply {
            address.setOnClickListener {
                findNavController().navigate(R.id.action_payFragment_to_selectAddressFragment)
            }
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun bindData() {
        val listEvaluate = listOf(
            Evaluate("1", 1, "Truyện đẹp"),
            Evaluate("2", 1, "Lên top thôi"),
            Evaluate("3", 1, "Truyện hay lắm")
        )
        binding.apply {
            val listAdapterBookPay = ListAdapterBookPay()
            recyclerviewListProduct.layoutManager = LinearLayoutManager(root.context)
            listAdapterBookPay.submitList(listEvaluate)
            recyclerviewListProduct.adapter = listAdapterBookPay

        }
    }

    override fun destroy() {

    }
}
