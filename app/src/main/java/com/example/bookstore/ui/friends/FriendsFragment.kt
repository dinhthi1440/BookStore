package com.example.bookstore.ui.friends

import androidx.lifecycle.ViewModelProvider
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.databinding.FragmentFriendsBinding

class FriendsFragment:BaseFragment<FragmentFriendsBinding>(FragmentFriendsBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[FriendViewModel::class.java]

    override fun initData() {

    }

    override fun handleEvent() {

    }

    override fun bindData() {

    }

    override fun destroy() {

    }
}