package com.example.bookstore.ui.message

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.databinding.FragmentMessageBinding
import com.example.bookstore.models.Evaluate


class MessageFragment: BaseFragment<FragmentMessageBinding>(FragmentMessageBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[MessageViewModel::class.java]

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

            val listAdapterMessage = ListAdapterMessage()
            recyclerviewListMessage.layoutManager = LinearLayoutManager(root.context)
            listAdapterMessage.submitList(listEvaluate)
            recyclerviewListMessage.adapter = listAdapterMessage
        }
    }

    override fun destroy() {

    }
}