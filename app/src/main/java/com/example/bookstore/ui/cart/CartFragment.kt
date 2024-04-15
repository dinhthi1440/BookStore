package com.example.bookstore.ui.cart

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.R
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.databinding.FragmentCartBinding
import com.example.bookstore.models.Book
import com.example.bookstore.models.Cart
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartFragment: BaseFragment<FragmentCartBinding>(FragmentCartBinding::inflate) {
    override val viewModel by viewModel<CartViewModel>()
    private var listProduct = mutableListOf<Cart>()

    override fun initData() {

    }

    override fun handleEvent() {

    }

    override fun bindData() {
        binding.apply {
            viewModel.getCart("123456")
            viewModel.getResultCart.observe(viewLifecycleOwner){
                if(it.isEmpty()){
                    layoutPayment.visibility = View.GONE
                    recyclerviewListCart.visibility = View.GONE
                    txtvWarning.visibility = View.VISIBLE
                }else{
                    val listAdapterCart= ListAdapterCart(::toTalPrice, ::toTalQuantities)
                    recyclerviewListCart.layoutManager = LinearLayoutManager(root.context)
                    listAdapterCart.submitList(it)
                    recyclerviewListCart.adapter = listAdapterCart
                }
            }
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
            btnPay.setOnClickListener {
                if(listProduct.isNotEmpty()){
                    val bundle = bundleOf("listProduct" to listProduct)
                    findNavController().navigate(R.id.action_cartFragment_to_payFragment, bundle)
                }else{
                    Toast.makeText(context, "Bạn chưa chọn sản phẩm", Toast.LENGTH_SHORT).show()
                }
                
            }

        }
    }
    private fun toTalPrice(totalPrice: Double){
        binding.txtvTotal.text = totalPrice.toString() + "đ"
    }

    private fun toTalQuantities(totalQuantity: Int, listCart: List<Cart>){
        binding.btnPay.text = "Thanh toán ($totalQuantity)"
        listProduct = listCart.toMutableList()
    }
    override fun destroy() {

    }
}