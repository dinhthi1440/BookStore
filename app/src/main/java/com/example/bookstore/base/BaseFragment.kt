package com.example.bookstore.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.bookstore.extensions.confirmNumberPurchase

abstract class BaseFragment<VB: ViewBinding>(
    private val bindingInflater: (LayoutInflater) -> VB
):Fragment() {
    private var _binding: VB? = null
    protected val binding get() = _binding as VB
    protected abstract val viewModel: BaseViewModel
    protected fun dialog(context1: Context): Dialog {
        return Dialog(context1)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindData()
        handleEvent()
        destroy()
    }


    abstract fun initData()
    abstract fun handleEvent()
    abstract fun bindData()
    abstract fun destroy()
}