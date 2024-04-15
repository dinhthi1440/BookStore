package com.example.bookstore.ui.select_address

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.R
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.databinding.FragmentSelectAddressBinding
import com.example.bookstore.models.Evaluate
import com.example.bookstore.ui.pay.ListAdapterBookPay

class SelectAddressFragment
    : BaseFragment<FragmentSelectAddressBinding>(FragmentSelectAddressBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[SelectAddressViewModel::class.java]

    override fun initData() {

    }

    override fun handleEvent() {
        binding.apply {
            addNewAddress.setOnClickListener {
                findNavController().navigate(R.id.action_selectAddressFragment_to_newAddressFragment)
            }
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun bindData() {
//        val listEvaluate = listOf(
//            Evaluate("1", 1, "Truyện đẹp"),
//            Evaluate("2", 1, "Lên top thôi"),
//        )
        binding.apply {

            val listAdapterAddress = ListAdapterAddress()
            recyclerViewListAddress.layoutManager = LinearLayoutManager(root.context)
            //listAdapterAddress.submitList(listEvaluate)
            recyclerViewListAddress.adapter = listAdapterAddress

        }
    }

    override fun destroy() {

    }
}