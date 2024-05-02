package com.example.bookstore.ui.chatting

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.databinding.FragmentChattingBinding
import com.example.bookstore.extensions.getUserID
import com.example.bookstore.models.Messages
import com.example.bookstore.models.User
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChattingFragment :BaseFragment<FragmentChattingBinding>(FragmentChattingBinding::inflate) {
    override val viewModel by viewModel<ChattingViewModel>()
    private val friendUser by lazy {  arguments?.getSerializable("user") as User }
    private val listAdapter by lazy { ListAdapterMessage(sharedPreferences.getUserID()!!) }
    override fun initData() {

    }

    override fun handleEvent() {
        binding.apply {
            imgBack.setOnClickListener { findNavController().popBackStack() }
            imgSend.setOnClickListener {
                if(edtComment.text.toString()!=""){
                    viewModel.addMessage(sharedPreferences.getUserID()!!, friendUser.userID,
                        edtComment.text.toString(), friendUser.imageUser, friendUser.userName, "")
                    edtComment.setText("")
                }
            }
        }
        viewModel.getMessages(sharedPreferences.getUserID()!!, friendUser.userID).observe(viewLifecycleOwner){
            initRecyclerView(it)
        }

    }

    override fun bindData() {
        binding.apply {
            Glide.with(requireContext()).load(friendUser.imageUser).into(imgUser)
            txtvUserName.text = friendUser.userName
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initRecyclerView(list: List<Messages>) {
        val layoutManager = LinearLayoutManager(context)
        binding.recyclerviewListMessages.layoutManager = layoutManager
        layoutManager.stackFromEnd = true
        listAdapter.setList(list)
        listAdapter.notifyDataSetChanged()
        binding.recyclerviewListMessages.adapter = listAdapter

    }
    override fun destroy() {

    }


}