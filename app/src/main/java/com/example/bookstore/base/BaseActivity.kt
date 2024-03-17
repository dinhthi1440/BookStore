package com.example.bookstore.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB: ViewBinding>(
    private val bindingInflater: (LayoutInflater) -> VB
):AppCompatActivity() {
    private var _binding: VB? = null
    protected val binding get() = _binding as VB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater(layoutInflater)
        setContentView(binding.root)
        initData()
        handleData()
        bindData()
    }
    abstract fun initData()
    abstract fun handleData()
    abstract fun bindData()
}

