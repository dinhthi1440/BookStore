package com.example.bookstore.ui.select_address

import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.R
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.databinding.FragmentSelectAddressBinding
import com.example.bookstore.extensions.confirmCancelOrder
import com.example.bookstore.extensions.getAddressID
import com.example.bookstore.extensions.getUserID
import com.example.bookstore.extensions.saveAddressID
import com.example.bookstore.models.Address
import org.koin.androidx.viewmodel.ext.android.viewModel

class SelectAddressFragment
    : BaseFragment<FragmentSelectAddressBinding>(FragmentSelectAddressBinding::inflate) {
    override val viewModel by viewModel<SelectAddressViewModel>()

    override fun initData() {

    }
    override fun handleEvent() {
        var isDelete = false
        binding.apply {
            addNewAddress.setOnClickListener {
                findNavController().navigate(R.id.action_selectAddressFragment_to_newAddressFragment)
            }
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
            imgDelete.setOnClickListener {
                if(isDelete){
                    bindAdapter("Xóa")
                }else{
                    bindAdapter("Sửa")
                }
                isDelete = !isDelete
            }
        }
    }
    override fun bindData() {
        viewModel.getAddresses(sharedPreferences.getUserID()!!)
        binding.apply {
            viewModel.getAddressesResult.observe(viewLifecycleOwner){
                if(sharedPreferences.getAddressID()==""){
                    sharedPreferences.saveAddressID(it[0].id)
                }
                val listAdapterAddress = ListAdapterAddress(sharedPreferences.getAddressID()!!, "Sửa", ::itemClickEdit)
                recyclerViewListAddress.layoutManager = LinearLayoutManager(root.context)
                listAdapterAddress.submitList(it)
                recyclerViewListAddress.adapter = listAdapterAddress
                //bindAdapter("Sửa")
            }
        }
    }
    private fun itemClickEdit(address: Address, deleteOrEdit: String){
        if(deleteOrEdit=="Xóa"){
            dialog(requireContext()).confirmCancelOrder("Bạn có chắc chắn muốn xóa địa chỉ này không?"){
                if(it=="yes"){
                    viewModel.deleteAddress(sharedPreferences.getUserID()!!, address)
                    viewModel.deleteAddressResult.observe(viewLifecycleOwner){
                        Toast.makeText(context, "Đã xóa thành công", Toast.LENGTH_SHORT).show()
                        bindAdapter("Xóa")
                    }
                }
            }
        }else if(deleteOrEdit==""){
            sharedPreferences.saveAddressID(address.id)
            bindAdapter("Sửa")
        }
        else {
            val bundle = bundleOf("address" to address)
            findNavController().navigate(R.id.action_selectAddressFragment_to_newAddressFragment, bundle)
        }
    }

    private fun bindAdapter(title: String){
        val listAdapterAddress = ListAdapterAddress(sharedPreferences.getAddressID()!!, title, ::itemClickEdit)
        binding.recyclerViewListAddress.layoutManager = LinearLayoutManager(binding.root.context)
        listAdapterAddress.submitList(viewModel.getAddressesResult.value)
        binding.recyclerViewListAddress.adapter = listAdapterAddress
    }
    override fun destroy() {

    }
}