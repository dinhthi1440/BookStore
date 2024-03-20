package com.example.bookstore.ui.account_infor

import androidx.lifecycle.ViewModelProvider
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.databinding.FragmentAccountInformationBinding


class AccountInformationFragment
    : BaseFragment<FragmentAccountInformationBinding>(FragmentAccountInformationBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[AccountInforVIewModel::class.java]

    override fun initData() {

    }

    override fun handleEvent() {

    }

    override fun bindData() {

    }

    override fun destroy() {

    }
}