package com.example.bookstore.ui.notify

import androidx.lifecycle.ViewModelProvider
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.databinding.FragmentNotifyBinding

class NotifyFragment :BaseFragment<FragmentNotifyBinding>(FragmentNotifyBinding::inflate){
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[NotifyViewModel::class.java]

    override fun initData() {

    }

    override fun handleEvent() {

    }

    override fun bindData() {

    }

    override fun destroy() {

    }

}