package com.example.bookstore.ui.chatting

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.databinding.FragmentChattingBinding
import com.example.bookstore.models.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChattingFragment :BaseFragment<FragmentChattingBinding>(FragmentChattingBinding::inflate) {
    override val viewModel by viewModel<ChattingViewModel>()
    private val friendUser by lazy {  arguments?.getSerializable("user") as User }
    val database = Firebase.database
    val myRef = database.getReference("message")
    override fun initData() {

    }

    override fun handleEvent() {
        binding.apply {
            imgBack.setOnClickListener { findNavController().popBackStack() }
            imgSend.setOnClickListener {
                myRef.setValue("Hello, World!")
                Toast.makeText(context, "Đã gửi", Toast.LENGTH_SHORT).show()
                edtComment.setText("")
            }
        }


    }

    override fun bindData() {
        binding.apply {
            Glide.with(requireContext()).load(friendUser.imageUser).into(imgUser)
            txtvUserName.text = friendUser.userName
        }
        myRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                binding.test.text = snapshot.getValue<String>()
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    override fun destroy() {

    }
}