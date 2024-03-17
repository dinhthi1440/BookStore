package com.example.bookstore.ui.book_detail

import android.app.Dialog
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.R
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.databinding.FragmentBookDetailBinding
import com.example.bookstore.extensions.confirmNumberPurchase
import com.example.bookstore.models.Evaluate


class BookDetailFragment:BaseFragment<FragmentBookDetailBinding>(FragmentBookDetailBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[BookDetailViewModel::class.java]

    override fun initData() {
    }

    override fun handleEvent() {

    }

    override fun bindData() {
        var isFavourite = false
        val listEvaluate = listOf(
            Evaluate("1", 1, "Truyện đẹp"),
            Evaluate("2", 1, "Lên top thôi"),
            Evaluate("3", 1, "Truyện hay lắm"),
            Evaluate("4", 1, "Hay quá trời hay"),
            Evaluate("5", 1, "Hay lắm fen"),
        )
        binding.apply {

            val listAdapterEvaluate= ListAdapterEvaluate()
            recyclerviewListEvaluate.layoutManager = LinearLayoutManager(root.context)
            listAdapterEvaluate.submitList(listEvaluate)
            recyclerviewListEvaluate.adapter = listAdapterEvaluate
            imgFavourite.setOnClickListener {
                if(isFavourite){
                    isFavourite = false
                    imgFavourite.setImageResource(R.drawable.heart_grey)
                }else{
                    isFavourite = true
                    imgFavourite.setImageResource(R.drawable.heart_red)
                }
            }
           // val dialog = context?.let { Dialog(it) }
            order.setOnClickListener {
                dialog(requireContext()).confirmNumberPurchase()

            }
            purchase.setOnClickListener {
                findNavController().navigate(R.id.action_bookDetailFragment_to_cartFragment)
            }
        }
    }

    override fun destroy() {

    }

}