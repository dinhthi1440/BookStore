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
import com.example.bookstore.extensions.getCurrentDateTime
import com.example.bookstore.models.Cart
import com.example.bookstore.models.Order
import com.example.bookstore.models.Voucher
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar
import kotlin.random.Random

class PayFragment:BaseFragment<FragmentPayBinding>(FragmentPayBinding::inflate) {
    override val viewModel by viewModel<PayViewModel>()

    private val listAdapterBookPay by lazy { ListAdapterBookPay(::listCart) }
    private var totalPriceMain = 0.0
    private val listProduct: MutableList<Cart>? by lazy {
        arguments?.getSerializable("listProduct") as? MutableList<Cart>
    }
    private var _listProduct = mutableListOf<Cart>()
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
                for (book in _listProduct){

                }

                binding.apply {
                    if(txtvAddress.text != "" && txtvNumberPhone.text !=""){
                        val order = Order(
                            Random.generateIDPayment(), "123456",
                            "Chờ xác nhận đơn hàng",_listProduct, totalPriceMain,
                            transportFee, totalPayment, getVoucher, payMethod,
                            Calendar.getInstance().getCurrentDateTime(), "Hà Nội",
                            txtvAddress.text.toString(),
                            txtvNumberPhone.text.toString(), txtvUsername.text.toString()
                        )
                        viewModel.addNewOrder(order)
                        viewModel.getAddResult.observe(viewLifecycleOwner){
                            viewModel.deleteCarts("123456", listCartID)
                            viewModel.getDeleteCartResult.observe(viewLifecycleOwner){
                                Toast.makeText(context, "Đã xoá thành công", Toast.LENGTH_SHORT).show()
                                findNavController().popBackStack()
                            }
                        }
                    }
                }
            }

        }
    }

    @SuppressLint("SetTextI18n")
    override fun bindData() {
        setFragmentResultListener("getVoucher") { requestKey, bundle ->
            val result = bundle.getSerializable("voucher") as? Voucher
            if(result!=null) {
                getVoucher = result
                binding.txtvVoucher.text = "-${getVoucher.maxDiscount}đ"
                val price = totalPriceMain + transportFee - getVoucher.maxDiscount
                binding.txtvTotalPay.text = price.toString() +"đ"
                binding.txtvTotalPriceMain.text = price.toString() +"đ"
            }
        }
        listCartID = mutableListOf()
        for (cart in listProduct!!){
            listCartID.add(cart.cardID)
        }
        Log.e("TAG", "bindData: $listProduct", )
        binding.txtvVoucher.text = "-${getVoucher.maxDiscount}đ"
        val price = totalPriceMain + transportFee - getVoucher.maxDiscount
        totalPayment = price
        binding.txtvTotalTransportFee.text = transportFee.toString() + "đ"
        binding.txtvTotalPay.text = price.toString() +"đ"
        binding.txtvTotalPriceMain.text = price.toString() +"đ"
        binding.checkboxBtnCash.isChecked=true
        binding.apply {
            recyclerviewListProduct.layoutManager = LinearLayoutManager(root.context)
            if(_listProduct.isEmpty()){
                Log.e("TAG", "bindData: 1", )
                listAdapterBookPay.submitList(listProduct)
            }else{
                Log.e("TAG", "bindData: 2", )
                listAdapterBookPay.submitList(_listProduct)

            }
            recyclerviewListProduct.adapter = listAdapterBookPay
        }

    }
    @SuppressLint("SetTextI18n")
    private fun listCart(listCart: List<Cart>, totalPrice: Double){
        binding.txtvTotalProduct.text = totalPrice.toString() +"đ"
        totalPriceMain = totalPrice
        Log.e("TAG", "listCart: ${listCart.size}", )
        val price = (totalPriceMain + transportFee - getVoucher.maxDiscount)
        totalPayment = price
        binding.txtvTotalPriceMain.text = price.toString() +"đ"
        binding.txtvTotalPay.text = price.toString() +"đ"
        _listProduct = listCart.toMutableList()
    }

    override fun destroy() {

    }
}
