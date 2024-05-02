package com.example.bookstore.ui.notify

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.databinding.FragmentNotifyBinding
import com.example.bookstore.extensions.getUserID
import com.example.bookstore.models.Evaluate
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotifyFragment :BaseFragment<FragmentNotifyBinding>(FragmentNotifyBinding::inflate){
    override val viewModel by viewModel<NotifyViewModel>()

    override fun initData() {

    }

    override fun handleEvent() {

    }

    override fun bindData() {
        viewModel.getNotify(sharedPreferences.getUserID()!!)
        val listAdapterNotification = ListAdapterNotification()
        binding.apply {
            viewModel.getNotifyResult.observe(viewLifecycleOwner){
                recyclerviewListNotify.layoutManager = LinearLayoutManager(root.context)
                listAdapterNotification.submitList(it)
                recyclerviewListNotify.adapter = listAdapterNotification
            }
        }
    }

    override fun destroy() {

    }

}