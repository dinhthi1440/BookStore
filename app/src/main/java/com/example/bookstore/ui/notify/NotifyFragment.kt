package com.example.bookstore.ui.notify

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.databinding.FragmentNotifyBinding
import com.example.bookstore.models.Evaluate

class NotifyFragment :BaseFragment<FragmentNotifyBinding>(FragmentNotifyBinding::inflate){
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[NotifyViewModel::class.java]

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

            val listAdapterNotification = ListAdapterNotification()
            recyclerviewListNotify.layoutManager = LinearLayoutManager(root.context)
            listAdapterNotification.submitList(listEvaluate)
            recyclerviewListNotify.adapter = listAdapterNotification
        }
    }

    override fun destroy() {

    }

}