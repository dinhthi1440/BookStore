package com.example.bookstore.ui.cart

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.R
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.databinding.FragmentCartBinding
import com.example.bookstore.extensions.confirmNumberPurchase
import com.example.bookstore.models.Evaluate
import com.example.bookstore.ui.book_detail.ListAdapterEvaluate

class CartFragment: BaseFragment<FragmentCartBinding>(FragmentCartBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[CartViewModel::class.java]

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
            var isSelected = false
            val listAdapterCart= ListAdapterCart()
            recyclerviewListCart.layoutManager = LinearLayoutManager(root.context)
            listAdapterCart.submitList(listEvaluate)
            recyclerviewListCart.adapter = listAdapterCart
            radioBtnSelectAll.setOnClickListener {
                if(isSelected){
                    radioBtnSelectAll.isChecked = false
                    isSelected = true
                }else{
                    isSelected = false
                }
            }
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
            btnPay.setOnClickListener {
                findNavController().navigate(R.id.action_cartFragment_to_payFragment)
            }

        }
    }

    override fun destroy() {

    }
}