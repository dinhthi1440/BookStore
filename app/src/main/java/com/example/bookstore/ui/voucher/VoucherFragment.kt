package com.example.bookstore.ui.voucher

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Looper
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.bookstore.R
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.databinding.FragmentVoucherBinding
import com.example.bookstore.extensions.openDlSuccess
import com.example.bookstore.models.Cart
import com.example.bookstore.models.Evaluate
import com.example.bookstore.models.Voucher
import com.example.bookstore.ui.pay.ListAdapterBookPay
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.logging.Handler

class VoucherFragment: BaseFragment<FragmentVoucherBinding>(FragmentVoucherBinding::inflate) {
    override val viewModel by viewModel<VoucherViewModel>()

    private val totalPrice: Double? by lazy { arguments?.getSerializable("orderPay") as? Double }
    private lateinit var listAdapterVoucher: ListAdapterVoucher
    override fun initData() {

    }

    override fun handleEvent() {
        binding.imgBack.setOnClickListener { findNavController().popBackStack() }

    }

    @SuppressLint("ResourceAsColor")
    override fun bindData() {
//        val listEvaluate = listOf(
//            Voucher("1", "Giảm giá vận chuyển", "Giảm 30.000đ phí vận chuyển đơn từ 150.000đ",
//                "30/4/2024", "1", 30, 150000.0, 30000.0),
//            Voucher("2", "Giảm giá vận chuyển", "Giảm 10.000đ phí vận chuyển cho tất cả các đơn",
//                "30/4/2024", "2", 0, 0.0, 10000.0)
//        )
//        for (i in listEvaluate){
//            viewModel.addNewVoucher(i)
//        }
//


        binding.apply {
            if(totalPrice!=null){
                listAdapterVoucher = ListAdapterVoucher(totalPrice!!, ::getVoucher)
            }else{
                listAdapterVoucher = ListAdapterVoucher(0.0, ::getVoucher)
            }

            viewModel.getVoucher()
            viewModel.getVoucherResult.observe(viewLifecycleOwner){
                recyclerviewListVoucher.layoutManager = LinearLayoutManager(root.context)
                listAdapterVoucher.submitList(it)
                recyclerviewListVoucher.adapter = listAdapterVoucher
            }
            swipeRefreshLayout.setColorSchemeColors(R.color.cus_green)
            android.os.Handler(Looper.getMainLooper()).postDelayed({
                swipeRefreshLayout.isRefreshing = false
            },3000 )
        }
    }
    private fun getVoucher(voucher: Voucher){
        setFragmentResult("getVoucher", bundleOf("voucher" to voucher))
        findNavController().popBackStack()
    }

    override fun destroy() {

    }
}