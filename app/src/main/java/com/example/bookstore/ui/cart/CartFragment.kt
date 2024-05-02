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
import com.example.bookstore.extensions.getUserID
import com.example.bookstore.models.Book
import com.example.bookstore.models.Cart
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartFragment: BaseFragment<FragmentCartBinding>(FragmentCartBinding::inflate) {
    override val viewModel by viewModel<CartViewModel>()
    private var listProduct = mutableListOf<Cart>()

    override fun initData() {
        viewModel.getCart(sharedPreferences.getUserID()!!)
    }

    override fun handleEvent() {

    }

    override fun bindData() {
        binding.apply {
            viewModel.getCart(sharedPreferences.getUserID()!!)
            viewModel.getResultCart.observe(viewLifecycleOwner){
                if(it.isEmpty()){
                    layoutPayment.visibility = View.GONE
                    recyclerviewListCart.visibility = View.GONE
                    txtvWarning.visibility = View.VISIBLE
                }else{
                    if(listProduct.isEmpty() || listProduct.size != it.size){
                        val listAdapterCart= ListAdapterCart(::onUpdateCart)
                        recyclerviewListCart.layoutManager = LinearLayoutManager(root.context)
                        listAdapterCart.submitList(it)
                        recyclerviewListCart.adapter = listAdapterCart
                    }else{
                        val listAdapterCart= ListAdapterCart(::onUpdateCart)
                        recyclerviewListCart.layoutManager = LinearLayoutManager(root.context)
                        listAdapterCart.submitList(listProduct)
                        recyclerviewListCart.adapter = listAdapterCart
                    }
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

    private fun onUpdateCart(cart: Cart, typeProcess: String){
        when(typeProcess){
            "addSelect" -> {
                listProduct.forEach {
                    if(it.cardID == cart.cardID)return
                }
                listProduct.add(cart)
            }
            "removeSelect" -> {
                listProduct.removeAll { it.cardID == cart.cardID }
            }
            "plusAndMinus" -> {
                listProduct.forEach {
                    if(it.cardID == cart.cardID){
                        it.quantity = cart.quantity
                    }
                }
            }
        }
        var totalPrice = 0.0
        listProduct.forEach {
            totalPrice +=it.quantity*it.book.price
        }
        binding.btnPay.text = "Thanh toán(${listProduct.size})"
        binding.txtvTotal.text = decimalFormat.format(totalPrice).toString() + "đ"
    }
    override fun destroy() {

    }
}