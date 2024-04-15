package com.example.bookstore.ui.friends

import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.R
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.databinding.FragmentFriendsBinding
import com.example.bookstore.extensions.generateRandomRatingID
import com.example.bookstore.extensions.getUserID
import com.example.bookstore.models.Book
import com.example.bookstore.models.Evaluate
import com.example.bookstore.models.FriendEvaluation
import com.example.bookstore.models.Rating
import com.example.bookstore.models.RatingDetail
import com.example.bookstore.ui.notify.ListAdapterNotification
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random

class FriendsFragment:BaseFragment<FragmentFriendsBinding>(FragmentFriendsBinding::inflate) {
    override val viewModel by viewModel<FriendViewModel>()

    override fun initData() {
        viewModel.getFriendEvaluation(sharedPreferences.getUserID()!!)
    }

    override fun handleEvent() {

    }

    override fun bindData() {
        val listAdapterFriendsEvaluate = ListAdapterFriendsEvaluate(::imgOnClick, ::clickLike,::clickCmt,
            sharedPreferences.getUserID()!!)
        binding.apply {
            viewModel.getFriendEvalResult.observe(viewLifecycleOwner){
                if(it.isEmpty()){
                    txtvNoData.visibility = View.VISIBLE
                    recyclerviewListEvaluateNewsfeed.visibility = View.GONE
                }else{
                    txtvNoData.visibility = View.GONE
                    recyclerviewListEvaluateNewsfeed.visibility = View.VISIBLE
                    recyclerviewListEvaluateNewsfeed.layoutManager = LinearLayoutManager(root.context)
                    listAdapterFriendsEvaluate.submitList(it)
                    recyclerviewListEvaluateNewsfeed.adapter = listAdapterFriendsEvaluate
                }
            }
            imgMessageFriend.setOnClickListener {
                findNavController().navigate(R.id.action_friendsFragment_to_shopMessageFragment)
            }
            imgListFriend.setOnClickListener {
                findNavController().navigate(R.id.action_friendsFragment_to_listFriendsFragment)
            }
        }
    }
    private fun imgOnClick(book: Book){
        val bundle = bundleOf("bookDetail" to book)
        findNavController().navigate(R.id.action_friendsFragment_to_bookDetailFragment, bundle)
    }
    private fun clickLike(rating: Rating, isLike: Boolean){
        viewModel.updateRating(rating, sharedPreferences.getUserID()!!, isLike)
        viewModel.getUpdateRatingResult.observe(viewLifecycleOwner){
            Toast.makeText(context, "Đã like thành công", Toast.LENGTH_SHORT).show()
        }
    }
    private fun clickCmt(friendEvaluation: FriendEvaluation){
        val bundle = bundleOf("rating" to RatingDetail(Random.generateRandomRatingID(),
            friendEvaluation.user, friendEvaluation.rating))
        findNavController().navigate(R.id.action_friendsFragment_to_replyRatingFragment, bundle)
    }
    override fun destroy() {

    }
}