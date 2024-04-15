package com.example.bookstore.ui.order_detail

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.databinding.FragmentOrderDetailBinding
import com.example.bookstore.models.Evaluate
import com.example.bookstore.models.Order
import com.example.bookstore.models.Voucher


class OrderDetailFragment:BaseFragment<FragmentOrderDetailBinding>(FragmentOrderDetailBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[OrderDetailViewModel::class.java]
    private val order by lazy { arguments?.getSerializable("order") as Order }
    private val listAdapterProductOrdered by lazy { ListAdapterProductOrdered() }

    override fun initData() {

    }

    override fun handleEvent() {
        binding.apply {
            imgBack.setOnClickListener { findNavController().popBackStack() }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun bindData() {
        binding.apply {
            recyclerViewProductOrdered.layoutManager = LinearLayoutManager(binding.root.context)
            listAdapterProductOrdered.submitList(order.listCart)
            recyclerViewProductOrdered.adapter = listAdapterProductOrdered
            txtvStatusOrderDetail.text = order.orderStatus
            txtvUserName.text = order.customerName
            txtvPhoneNumber.text = order.customerPhoneNumber
            txtvAddress.text = order.customerAddress
            if(order.payMethod == "Thanh toán khi nhận hàng"){
                txtvTotalAmountOrder.text = order.totalPayment.toString() + "đ"
                txtvNotifyTotal.text = "Vui lòng thanh toán ${order.totalPayment}đ khi nhận hàng"
            }else{
                txtvTotalAmountOrder.text = "0đ"
                txtvNotifyTotal.text = "Bạn đã thanh toán ${order.totalPayment}đ qua thẻ ngân hàng"
            }
            txtvPayMethod.text = order.payMethod
            txtvIdOrder.text = order.id
            txtvTimeOrder.text = order.orderDate
            
        }
    }

    override fun destroy() {

    }
}