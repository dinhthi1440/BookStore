package com.example.bookstore.ui.my_friends
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.R
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.databinding.FragmentListFriendsBinding
import com.example.bookstore.extensions.getUserID
import com.example.bookstore.models.User
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListFriendsFragment:BaseFragment<FragmentListFriendsBinding>(FragmentListFriendsBinding::inflate) {
    override val viewModel by viewModel<ListFriendViewModel>()
    private val listFriend by lazy { ListAdapterFriend(::onClickItem) }

    override fun initData() {
    }

    override fun handleEvent() {
        binding.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun bindData() {
        bindAdapter()
    }
    private fun onClickItem(friendClicked: User, messClick: String){
        if(messClick == "chat"){
            val bundle = bundleOf("user" to friendClicked)
            findNavController().navigate(R.id.action_listFriendsFragment_to_chattingFragment, bundle)
        }else{
            viewModel.deleteFriends(sharedPreferences.getUserID()!!, friendClicked.userID)
            viewModel.getDeleFriendResult.observe(viewLifecycleOwner){
                if(it == 1){
                    Toast.makeText(context, "Huỷ theo dõi thành công", Toast.LENGTH_SHORT).show()
                    bindAdapter()
                }else{
                    Toast.makeText(context, "Huỷ theo dõi thất bại", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun bindAdapter(){
        viewModel.getListFriend(sharedPreferences.getUserID()!!)
        binding.apply {
            viewModel.getListFriendResult.observe(viewLifecycleOwner){
                recyclerviewListFriend.layoutManager = LinearLayoutManager(root.context)
                listFriend.submitList(it)
                recyclerviewListFriend.adapter = listFriend
            }
        }
    }

    override fun destroy() {

    }
}