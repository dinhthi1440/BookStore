package com.example.bookstore.ui.change_password

import androidx.lifecycle.ViewModelProvider
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.databinding.FragmentChangePasswordBinding

class ChangePasswordFragment :BaseFragment<FragmentChangePasswordBinding>(FragmentChangePasswordBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[ChangePassViewModel::class.java]

    override fun initData() {

    }

    override fun handleEvent() {

    }

    override fun bindData() {

    }

    override fun destroy() {

    }
}