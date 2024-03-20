package com.example.bookstore.ui.friends

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.R
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.databinding.FragmentFriendsBinding
import com.example.bookstore.models.Evaluate
import com.example.bookstore.ui.notify.ListAdapterNotification

class FriendsFragment:BaseFragment<FragmentFriendsBinding>(FragmentFriendsBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[FriendViewModel::class.java]

    override fun initData() {

    }

    override fun handleEvent() {

    }

    override fun bindData() {
        val listEvaluate = listOf(
            Evaluate("1", 1, "Truyện đẹp"),
            Evaluate("2", 1, "Lên top thôi"),
            Evaluate("3", 1, "Truyện hay lắm"),
        )
        binding.apply {

            val listAdapterFriendsEvaluate = ListAdapterFriendsEvaluate()
            recyclerviewListEvaluateNewsfeed.layoutManager = LinearLayoutManager(root.context)
            listAdapterFriendsEvaluate.submitList(listEvaluate)
            recyclerviewListEvaluateNewsfeed.adapter = listAdapterFriendsEvaluate

            imgMessageFriend.setOnClickListener {
                findNavController().navigate(R.id.action_friendsFragment_to_shopMessageFragment)
            }
        }
    }

    override fun destroy() {

    }
}