package com.example.bookstore.ui.order

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.base.BaseAdapter
import com.example.bookstore.base.BaseViewHolder
import com.example.bookstore.databinding.ItemOrderedBinding
import com.example.bookstore.models.Evaluate
import com.example.bookstore.models.Order
import com.example.bookstore.ui.order_detail.ListAdapterProductOrdered

class ListAdapterOrder(private val onClickItemOrder: (Order) -> Unit,
                       private val onClickCancelOrder: (Order) -> Unit): BaseAdapter<Order, BaseViewHolder<Order>>(Order.differUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Order> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemOrderedBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(val binding: ItemOrderedBinding) :
        BaseViewHolder<Order>(binding) {
        override fun bindView(item: Order, isItemSelected: Boolean) {
            super.bindView(item, isItemSelected)
            val listAdapterProductOrdered = ListAdapterProductOrdered()
            binding.apply {
                recyclerviewListItemOrder.layoutManager = LinearLayoutManager(root.context)
                listAdapterProductOrdered.submitList(item.listCart)
                recyclerviewListItemOrder.adapter = listAdapterProductOrdered
                root.setOnClickListener {
                    onClickItemOrder(item)
                }
                txtvStatusOrderDetail.text = item.orderStatus
                txtvTotalQuantity.text = "${item.listCart.size} sản phẩm"
                txtvTotalAmountOrder.text = item.totalPayment.toString()
            }
            binding.btnCancelOrder.setOnClickListener {
                onClickCancelOrder(item)
            }

        }
    }
}