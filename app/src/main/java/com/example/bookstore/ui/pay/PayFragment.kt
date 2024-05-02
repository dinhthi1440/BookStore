package com.example.bookstore.ui.pay

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.R
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.databinding.FragmentPayBinding
import com.example.bookstore.extensions.generateIDPayment
import com.example.bookstore.extensions.getAddressID
import com.example.bookstore.extensions.getCurrentDateTime
import com.example.bookstore.extensions.getUserID
import com.example.bookstore.models.Cart
import com.example.bookstore.models.Order
import com.example.bookstore.models.Voucher
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar
import kotlin.random.Random

class PayFragment:BaseFragment<FragmentPayBinding>(FragmentPayBinding::inflate) {
    override val viewModel by viewModel<PayViewModel>()

    private val listAdapterBookPay by lazy { ListAdapterBookPay(::onUpdatePayment) }
    private var totalPriceMain = 0.0
    private val listProduct: MutableList<Cart>? by lazy {
        arguments?.getSerializable("listProduct") as? MutableList<Cart>
    }
    private var getVoucher = Voucher()
    private val transportFee= 40000.0
    private var totalPayment= 0.0
    private var payMethod = ""
    private lateinit var listCartID: MutableList<String>
    override fun initData() {

    }

    override fun handleEvent() {
        binding.apply {
            address.setOnClickListener {
                findNavController().navigate(R.id.action_payFragment_to_selectAddressFragment)
            }
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
            checkboxBtnCash.setOnCheckedChangeListener { button, b ->
                checkboxBtnCash1.isChecked = !b

            }
            checkboxBtnCash1.setOnCheckedChangeListener { button, b ->
                checkboxBtnCash.isChecked = !b
            }

            layoutVoucher.setOnClickListener {
                val bundle = bundleOf("orderPay" to totalPriceMain)
                findNavController().navigate(R.id.action_payFragment_to_voucherFragment, bundle)
            }
            layoutOrder.setOnClickListener {
                if(checkboxBtnCash.isChecked){
                    payMethod = binding.txtvBtnCash.text.toString()
                }else{
                    payMethod = binding.txtvBtnCard.text.toString()
                }
                if(txtvAddress.text != ""){
                    binding.apply {
                        if(txtvAddress.text != "" && txtvNumberPhone.text !=""){
                            val order = listProduct?.let { it1 ->
                                Order(
                                    Random.generateIDPayment(), sharedPreferences.getUserID()!!,
                                    "Chờ xác nhận đơn hàng", it1.toList(), totalPriceMain,
                                    transportFee, totalPayment, getVoucher, payMethod,
                                    Calendar.getInstance().getCurrentDateTime(), "Hà Nội",
                                    txtvAddress.text.toString(),
                                    txtvNumberPhone.text.toString(), txtvUsername.text.toString()
                                )
                            }
                            viewModel.addNewOrder(order!!)
                            viewModel.getAddResult.observe(viewLifecycleOwner){
                                viewModel.deleteCarts(sharedPreferences.getUserID()!!, listCartID)
                                viewModel.getDeleteCartResult.observe(viewLifecycleOwner){
                                    findNavController().popBackStack()
                                }
                            }
                        }
                    }
                }else{
                    Toast.makeText(context, "Hãy chọn địa chỉ", Toast.LENGTH_SHORT).show()
                }

            }

        }
    }

    @SuppressLint("SetTextI18n")
    override fun bindData() {
        viewModel.getAddresses(sharedPreferences.getUserID()!!)
        viewModel.getAddressesResult.observe(viewLifecycleOwner){
            val address = it.find { itemAddress -> itemAddress.id == sharedPreferences.getAddressID() }
            if(address!=null){
                binding.apply {
                    txtvUsername.text = address.name + " |"
                    txtvNumberPhone.text = address.phoneNumber
                    txtvAddress.text = "${address.detailDescription}, ${address.communeOrAward}, ${address.district}, ${address.province}"
                }
            }
        }
        voucherResult()
        listCartID = mutableListOf()
        for (cart in listProduct!!){
            listCartID.add(cart.cardID)
            onUpdatePayment(cart)
        }

        binding.txtvVoucher.text = "-${getVoucher.maxDiscount}đ"
        val price = totalPriceMain + transportFee - getVoucher.maxDiscount
        totalPayment = price

        binding.apply {
            txtvTotalTransportFee.text = decimalFormat.format(transportFee).toString() + "đ"
            txtvTotalPay.text = decimalFormat.format(price).toString() +"đ"
            txtvTotalPriceMain.text = decimalFormat.format(price).toString() +"đ"
            checkboxBtnCash.isChecked=true
            recyclerviewListProduct.layoutManager = LinearLayoutManager(root.context)
            listAdapterBookPay.submitList(listProduct)
            recyclerviewListProduct.adapter = listAdapterBookPay
        }

    }
    @SuppressLint("SetTextI18n")
    private fun onUpdatePayment(cart: Cart){
        var totalPrice = 0.0
        listProduct?.forEach {
            if(it.cardID == cart.cardID){
                it.quantity = cart.quantity
            }
            totalPrice +=it.quantity*it.book.price
        }
        binding.txtvTotalProduct.text = decimalFormat.format(totalPrice).toString() +"đ"
        totalPriceMain = totalPrice
        val price = (totalPriceMain + transportFee - getVoucher.maxDiscount)
        totalPayment = price
        binding.txtvTotalPriceMain.text = decimalFormat.format(price).toString() +"đ"
        binding.txtvTotalPay.text = decimalFormat.format(price).toString() +"đ"
    }

    private fun voucherResult(){
        setFragmentResultListener("getVoucher") { requestKey, bundle ->
            val result = bundle.getSerializable("voucher") as? Voucher
            if(result!=null) {
                getVoucher = result
                binding.txtvVoucher.text = "-${getVoucher.maxDiscount}đ"
                val price = totalPriceMain + transportFee - getVoucher.maxDiscount
                binding.txtvTotalPay.text = decimalFormat.format(price).toString() +"đ"
                binding.txtvTotalPriceMain.text = decimalFormat.format(price).toString() +"đ"
            }
        }
    }

    override fun destroy() {

    }
}
