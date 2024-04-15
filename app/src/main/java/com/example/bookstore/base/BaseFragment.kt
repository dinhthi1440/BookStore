package com.example.bookstore.base

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.bookstore.extensions.openDlLoading
import org.koin.android.ext.android.get

abstract class BaseFragment<VB: ViewBinding>(
    private val bindingInflater: (LayoutInflater) -> VB
):Fragment() {
    private var _binding: VB? = null
    protected val binding get() = _binding as VB
    protected abstract val viewModel: BaseViewModel
    protected val sharedPreferences by lazy { get<SharedPreferences>() }
    private val dialog by lazy{context?.let { Dialog(it) }}
    protected fun dialog(context1: Context): Dialog {
        return Dialog(context1)
    }
    protected fun showKeyboard(context1: Context) {
        val inputMethodManager = context1?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    protected fun hideKeyboard(view: View) {
        val inputMethodManager = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
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
        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                dialog?.openDlLoading(false)
            } else {
                dialog?.dismiss()
            }
        }
        bindData()
        handleEvent()
        destroy()
    }


    abstract fun initData()
    abstract fun handleEvent()
    abstract fun bindData()
    abstract fun destroy()
}