package com.example.bookstore.ui.message

import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.R
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.databinding.FragmentMessageBinding
import com.example.bookstore.extensions.getUserID
import org.koin.androidx.viewmodel.ext.android.viewModel


class MessageFragment: BaseFragment<FragmentMessageBinding>(FragmentMessageBinding::inflate) {
    override val viewModel by viewModel<MessageViewModel>()

    override fun initData() {
        viewModel.getListFriend(sharedPreferences.getUserID()!!)
    }

    override fun handleEvent() {
        binding.apply {
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun bindData() {
        initRecycleView()
    }

    private fun initRecycleView(){
        viewModel.getAllChatList(sharedPreferences.getUserID()!!).observe(viewLifecycleOwner){
            val listAdapterMessageBox = ListAdapterMessageBox(::onClickItem)
            binding.recyclerviewListMessage.layoutManager = LinearLayoutManager(binding.root.context)
            listAdapterMessageBox.submitList(it)
            binding.recyclerviewListMessage.adapter = listAdapterMessageBox
        }
    }

    private fun onClickItem(friendID: String){
        viewModel.getListUserResult.observe(viewLifecycleOwner){ listFriend ->
            for(user in listFriend){
                if(user.userID == friendID){
                    val bundle = bundleOf("user" to user)
                    findNavController().navigate(R.id.action_shopMessageFragment_to_chattingFragment, bundle)
                }
            }
        }
    }
    override fun destroy() {

    }
}