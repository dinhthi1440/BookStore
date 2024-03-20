package com.example.bookstore.ui.voucher

import android.annotation.SuppressLint
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.bookstore.R
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.databinding.FragmentVoucherBinding
import com.example.bookstore.extensions.openDlSuccess
import com.example.bookstore.models.Evaluate
import com.example.bookstore.ui.pay.ListAdapterBookPay
import java.util.logging.Handler

class VoucherFragment: BaseFragment<FragmentVoucherBinding>(FragmentVoucherBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[VoucherViewModel::class.java]

    override fun initData() {

    }

    override fun handleEvent() {
        binding.imgBack.setOnClickListener { findNavController().popBackStack() }
    }

    @SuppressLint("ResourceAsColor")
    override fun bindData() {
        val listEvaluate = listOf(
            Evaluate("1", 1, "Truyện đẹp"),
            Evaluate("2", 1, "Lên top thôi"),
            Evaluate("3", 1, "Truyện hay lắm")
        )

        binding.apply {

            val listAdapterVoucher = ListAdapterVoucher()
            recyclerviewListVoucher.layoutManager = LinearLayoutManager(root.context)
            listAdapterVoucher.submitList(listEvaluate)
            recyclerviewListVoucher.adapter = listAdapterVoucher


            swipeRefreshLayout.setColorSchemeColors(R.color.cus_green)
            android.os.Handler(Looper.getMainLooper()).postDelayed({
                swipeRefreshLayout.isRefreshing = false
            },3000 )
        }
    }

    override fun destroy() {

    }
}