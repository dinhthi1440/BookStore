package com.example.bookstore.ui.home

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.RemoteViews
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.R
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.databinding.FragmentHomeBinding
import com.example.bookstore.extensions.getUserID
import com.example.bookstore.extensions.openDlSuccess
import com.example.bookstore.extensions.scanQRResult
import com.example.bookstore.models.Book
import com.example.bookstore.models.BookGenres
import com.example.bookstore.models.District
import com.example.bookstore.ui.main.MainActivity
import com.example.bookstore.util.Constant
import com.example.bookstore.util.JsonFileProcess
import io.github.g00fy2.quickie.QRResult
import io.github.g00fy2.quickie.ScanQRCode
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment :BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    override val viewModel by viewModel<HomeViewModel>()

    private val nameTypeBookAdapter by lazy{
        ListAdapterBookTypeName()
    }
    private val listTypeBookAdapter by lazy{
        ListAdapterBookType(::onClickItem)
    }
    override fun initData() {
        viewModel.getBookByGenre()
    }

    override fun handleEvent() {
        val jsonString = JsonFileProcess().readJsonFromAssets(requireContext(), "quan_huyen.json")
        val districts: List<District> = JsonFileProcess().parseJsonToModel(jsonString)
        var nameDistrict = ""
        binding.apply {
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
            shopLocation.setOnClickListener {
                //notification()
            }
            edittextSearch.setOnClickListener {
                Toast.makeText(context, "Chạm", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
            }

        }
    }

    override fun bindData() {
        viewModel.getBookByGenre.observe(viewLifecycleOwner){
            binding.recyclerviewListTypeBook.layoutManager = LinearLayoutManager(context)
            listTypeBookAdapter.submitList(it)
            binding.recyclerviewListTypeBook.adapter = listTypeBookAdapter
        }

        binding.apply {
//            recycleviewNameTypeBook.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//            recycleviewNameTypeBook.adapter = nameTypeBookAdapter
//            nameTypeBookAdapter.submitList(listName)

//            val user = User(
//                sharedPreferences.getUserID()!!, Random.generateRandomCustomerID(),
//                "", "", "", email, "", "")
//            viewModel.addUser(user)

        }


    }

    private val scanQrCodeLauncher = registerForActivityResult(ScanQRCode()) { result ->
        handleQRResult(result)
    }

    private fun handleQRResult(result: QRResult) {
        when (result) {
            is QRResult.QRSuccess -> {
                val rawValue = result.content.rawValue
                rawValue?.let { viewModel.getUserByCusID(it) }
                viewModel.getUserResult.observe(viewLifecycleOwner){
                    dialog(requireContext()).scanQRResult(it){ user, mess ->
                        if(mess == "follow"){
                            viewModel.addFriends(sharedPreferences.getUserID()!!, user.userID)
                            viewModel.addFollowResult.observe(viewLifecycleOwner){
                                dialog(requireContext()).openDlSuccess()
                                Toast.makeText(context, "Follow thành công", Toast.LENGTH_SHORT).show()

                            }
                        }else if(mess == "chatting"){
                            val bundle = bundleOf("user" to user)
                            findNavController().navigate(R.id.action_homeFragment_to_chattingFragment, bundle)
                        }
                    }
                }
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
    private fun onClickItem(isShowMoreSelected: Boolean, bookGenres: BookGenres, book: Book){
        if(isShowMoreSelected){
            val bundle = bundleOf("bookGenre" to bookGenres)
            findNavController().navigate(R.id.action_homeFragment_to_showAllBookFragment, bundle)
        }else{
            val bundle = bundleOf("bookDetail" to book)
            findNavController().navigate(R.id.action_homeFragment_to_bookDetailFragment, bundle)
        }

    }

    override fun destroy() {

    }
}