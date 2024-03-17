package com.example.bookstore.ui.setting

import androidx.lifecycle.ViewModelProvider
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.databinding.FragmentSettingBinding

class SettingFragment: BaseFragment<FragmentSettingBinding>(FragmentSettingBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[SettingViewModel::class.java]

    override fun initData() {

    }

    override fun handleEvent() {

    }

    override fun bindData() {

    }

    override fun destroy() {

    }
}