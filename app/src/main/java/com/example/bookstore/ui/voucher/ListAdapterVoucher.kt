package com.example.bookstore.ui.voucher

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bookstore.R
import com.example.bookstore.base.BaseAdapter
import com.example.bookstore.base.BaseViewHolder
import com.example.bookstore.databinding.ItemVoucherBinding
import com.example.bookstore.models.Voucher

class ListAdapterVoucher(private val totalPrice: Double, private val getVoucher: (Voucher) -> Unit):
    BaseAdapter<Voucher, BaseViewHolder<Voucher>>(Voucher.differUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Voucher> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemVoucherBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(val binding: ItemVoucherBinding) :
        BaseViewHolder<Voucher>(binding) {
        override fun bindView(item: Voucher, isItemSelected: Boolean) {
            super.bindView(item, isItemSelected)
            binding.apply {
                txtvVoucherType.text = item.voucherType
                txtvConterVoucher.text = item.description
                txtvExpiryDate.text = item.expirationDate
                if(totalPrice!=0.0){
                    if(totalPrice>=item.minPrice){
                        txtvUse.setOnClickListener {
                            getVoucher(item)
                        }
                    }else{
                        txtvUse.setTextColor(R.color.grey.toInt())
                    }
                }
            }
        }
    }
}