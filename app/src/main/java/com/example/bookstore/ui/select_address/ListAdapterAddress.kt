package com.example.bookstore.ui.select_address

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bookstore.base.BaseAdapter
import com.example.bookstore.base.BaseViewHolder
import com.example.bookstore.databinding.ItemAddressBinding
import com.example.bookstore.databinding.ItemOrderPayBinding
import com.example.bookstore.models.Address
import com.example.bookstore.models.Evaluate

class ListAdapterAddress( private val addressSelected: String,
                          private val deleteStr: String,
    private val itemClickEdit: (Address, String) -> Unit): BaseAdapter<Address, BaseViewHolder<Address>>(Address.differUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Address> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAddressBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(val binding: ItemAddressBinding) :
        BaseViewHolder<Address>(binding) {
        override fun bindView(item: Address, isItemSelected: Boolean) {
            super.bindView(item, isItemSelected)
            binding.apply {
                if(addressSelected == item.id){
                    checkboxBtnSelect.isChecked = true
                }
                txtvEdit.text = deleteStr
                txtvUsernameAndNumberphone.text = "${item.name} | ${item.phoneNumber}"
                txtvAddress.text = "${item.detailDescription}, ${item.communeOrAward}, ${item.district}, ${item.province}"
                txtvEdit.setOnClickListener {
                    if(deleteStr =="Sửa"){
                        itemClickEdit(item, "Sửa")
                    }else{
                        itemClickEdit(item, "Xóa")
                    }
                }
                checkboxBtnSelect.setOnCheckedChangeListener { _, isChecked ->
                    if(!isChecked && addressSelected == item.id){
                        checkboxBtnSelect.isChecked = true
                    }else{
                        itemClickEdit(item, "")
                    }
                }
            }

        }
    }
}