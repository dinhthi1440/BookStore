package com.example.bookstore.ui.order

import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.R
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.databinding.FragmentOrderBinding
import com.example.bookstore.extensions.confirmCancelOrder
import com.example.bookstore.extensions.getUserID
import com.example.bookstore.models.Order
import org.koin.androidx.viewmodel.ext.android.viewModel

class OrderFragment : BaseFragment<FragmentOrderBinding>(FragmentOrderBinding::inflate) {
    override val viewModel by viewModel<OrderViewModel>()


    private val listAdapterOrder by lazy {
        ListAdapterOrder(::onClickItemOrder, ::onClickCancelOrder)
    }
    private var listOrder = mutableListOf<Order>()
    override fun initData() {
        
    }

    override fun handleEvent() {
        binding.apply {
            imgBack.setOnClickListener { findNavController().popBackStack() }
        }
    }

    override fun bindData() {
        viewModel.getOrder(sharedPreferences.getUserID()!!)
        viewModel.getOrderResult.observe(viewLifecycleOwner){
            listOrder = it.toMutableList()
            binding.apply {
                recyclerviewListCart.layoutManager = LinearLayoutManager(root.context)
                listAdapterOrder.submitList(listOrder)
                recyclerviewListCart.adapter = listAdapterOrder
            }
        }

    }
    private fun onClickItemOrder(order: Order){
        val bundle = bundleOf("order" to order)
        findNavController().navigate(R.id.action_orderFragment_to_orderDetailFragment, bundle)
    }
    private fun onClickCancelOrder(order: Order){
        dialog(requireContext()).confirmCancelOrder("Bạn có chắc chắn muốn huỷ đơn hàng này không?"){
            if(it=="yes"){
                viewModel.cancelOrder(sharedPreferences.getUserID()!!, order)
                viewModel.getCancelOrderResult.observe(viewLifecycleOwner){
                    Toast.makeText(context, "Huỷ thành công", Toast.LENGTH_SHORT).show()
                    viewModel.getOrder(sharedPreferences.getUserID()!!)
                    viewModel.getOrderResult.observe(viewLifecycleOwner){
                        listOrder = it.toMutableList()
                        binding.apply {
                            listAdapterOrder.submitList(listOrder)
                            recyclerviewListCart.adapter = listAdapterOrder
                        }
                    }
                }
            }
        }
    }

    override fun destroy() {
        
    }
}