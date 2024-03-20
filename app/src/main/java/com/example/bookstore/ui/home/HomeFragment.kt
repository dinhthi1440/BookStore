package com.example.bookstore.ui.home

import android.util.Log
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.R
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.databinding.FragmentHomeBinding
import com.example.bookstore.models.Book
import com.example.bookstore.models.TypeBook
import io.github.g00fy2.quickie.QRResult
import io.github.g00fy2.quickie.ScanQRCode
import io.github.g00fy2.quickie.content.QRContent

class HomeFragment :BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[HomeViewModel::class.java]

    private val nameTypeBookAdapter by lazy{
        ListAdapterBookTypeName()
    }
    private val listTypeBookAdapter by lazy{
        ListAdapterBookType(::onClickItem)
    }

    override fun initData() {

    }

    override fun handleEvent() {

    }

    override fun bindData() {
        var listName: List<String> = listOf("All", "Trinh thám", "Ngôn tình", "Tiểu thuyết", "Kinh dị")
        var bookTT = TypeBook(1, "Trinh thám", listOf(
            Book(1, "Đắc nhân tâm quyển 1", "Trinh thám"),
            Book(2, "Sách 2", "Trinh thám"),
        ))
        var bookNT = TypeBook(2, "Ngôn tình", listOf(
            Book(3, "Sách 3", "Ngôn tình"),
            Book(4, "Sách 4", "Ngôn tình"),
        ))

        binding.apply {
            recycleviewNameTypeBook.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recycleviewNameTypeBook.adapter = nameTypeBookAdapter
            nameTypeBookAdapter.submitList(listName)

            recyclerviewListTypeBook.layoutManager = LinearLayoutManager(root.context)
            listTypeBookAdapter.submitList(listOf(bookTT, bookNT))
            recyclerviewListTypeBook.adapter = listTypeBookAdapter

            cart.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_cartFragment)
            }
            voucher.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_voucherFragment)
            }
            orders.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_orderFragment)
            }
            QRCodeScanner.setOnClickListener {
                scanQrCodeLauncher.launch(null)
            }

        }


    }
    private val scanQrCodeLauncher = registerForActivityResult(ScanQRCode()) { result ->
        handleQRResult(result)
    }
    private fun handleQRResult(result: QRResult) {
        when (result) {
            is QRResult.QRSuccess -> {
                val rawValue = result.content.rawValue
                Toast.makeText(context, "Giá trị là: $rawValue", Toast.LENGTH_SHORT).show()
                Log.d("TAG", "handleQRResult: $rawValue", )
            }
            is QRResult.QRUserCanceled -> {
            }
            is QRResult.QRMissingPermission -> {

            }
            is QRResult.QRError -> {
                val exception = result.exception
            }
        }
    }
    private fun onClickItem(bookCategory: String){
        if(bookCategory!=""){
            val bundle = bundleOf("bookCategory" to bookCategory)
            findNavController().navigate(R.id.action_homeFragment_to_showAllBookFragment, bundle)
        }else{
            findNavController().navigate(R.id.action_homeFragment_to_bookDetailFragment)
        }

    }

    override fun destroy() {

    }
}