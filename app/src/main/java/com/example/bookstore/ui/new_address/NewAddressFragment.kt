package com.example.bookstore.ui.new_address

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.bookstore.R
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.databinding.FragmentNewAddressBinding


class NewAddressFragment :BaseFragment<FragmentNewAddressBinding>(FragmentNewAddressBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[NewAddressViewModel::class.java]

    override fun initData() {

    }

    override fun handleEvent() {
        binding.apply {
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
            btnAdd.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun bindData() {

    }

    override fun destroy() {

    }
}