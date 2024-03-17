package com.example.bookstore.ui.shop_message

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.databinding.FragmentShopMessageBinding
import com.example.bookstore.models.Evaluate


class ShopMessageFragment: BaseFragment<FragmentShopMessageBinding>(FragmentShopMessageBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[ShopMessageViewModel::class.java]

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

            val listAdapterShopMessage = ListAdapterShopMessage()
            recyclerviewListMessage.layoutManager = LinearLayoutManager(root.context)
            listAdapterShopMessage.submitList(listEvaluate)
            recyclerviewListMessage.adapter = listAdapterShopMessage
        }
    }

    override fun destroy() {

    }
}