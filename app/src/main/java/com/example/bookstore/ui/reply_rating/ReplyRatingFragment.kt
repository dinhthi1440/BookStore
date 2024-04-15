package com.example.bookstore.ui.reply_rating

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.bookstore.R
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.databinding.FragmentReplyRatingBinding
import com.example.bookstore.extensions.generateRandomCommentID
import com.example.bookstore.extensions.getCurrentDateTime
import com.example.bookstore.extensions.getUserID
import com.example.bookstore.models.Book
import com.example.bookstore.models.Comment
import com.example.bookstore.models.CommentDetail
import com.example.bookstore.models.Order
import com.example.bookstore.models.Rating
import com.example.bookstore.models.RatingDetail
import com.example.bookstore.models.User
import com.example.bookstore.ui.book_detail.BookDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar
import kotlin.random.Random

class ReplyRatingFragment:BaseFragment<FragmentReplyRatingBinding>(FragmentReplyRatingBinding::inflate) {
    override val viewModel by viewModel<BookDetailViewModel>()
    private val listAdapterComment by lazy { ListAdapterComment(::commentClick, ::followUser, ::deleteComment, ::clickLike,
        sharedPreferences.getUserID()!!) }
    private val ratingDetail by lazy { arguments?.getSerializable("rating") as RatingDetail }
    override fun initData() {
        viewModel.getComment(ratingDetail.rating)
    }


    override fun handleEvent() {
        var isLiked = ratingDetail.rating.likes.contains(sharedPreferences.getUserID())
        binding.apply {
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
            imgSend.setOnClickListener {
                viewModel.addComment(CommentDetail(
                        Random.generateRandomCommentID(),
                        ratingDetail.rating.id,
                        edtCommnet.text.toString(),
                        sharedPreferences.getUserID()!!,
                        Calendar.getInstance().getCurrentDateTime(),
                        mutableListOf()), ratingDetail.rating)
                viewModel.getResultAddComment.observe(viewLifecycleOwner){
                    Toast.makeText(context, "Thêm bình luận thành công", Toast.LENGTH_SHORT).show()
                }
                imgSend.visibility = View.GONE
                edtCommnet.setText("")
                hideKeyboard(requireView())
            }
            imgComment.setOnClickListener {
                binding.imgSend.visibility = View.VISIBLE
                binding.edtCommnet.setText("@${binding.txtvNameUser.text} ")
                showKeyboard(requireContext())
            }
            if(sharedPreferences.getUserID() == ratingDetail.user.userID){
                imgDots.setOnClickListener{
                    menuDot(root.context, it, ratingDetail.user, ratingDetail.rating)
                }
            }else{
                imgUser.setOnClickListener {
                    showPopupMenuFollow(root.context, it, ratingDetail.user)
                }
                txtvNameUser.setOnClickListener {
                    showPopupMenuFollow(root.context, it, ratingDetail.user)
                }
                imgDots.setOnClickListener{
                    menuDotNoDelete(root.context, it, ratingDetail.user)
                }

            }
            if(isLiked){
                imgLike.setImageResource(R.drawable.like_green_24)
            }
            imgLike.setOnClickListener {
                if (isLiked) {
                    txtvNumberLike.text = (txtvNumberLike.text.toString().toInt() - 1).toString()
                    imgLike.setImageResource(R.drawable.like_grey_24)
                    viewModel.updateRating(ratingDetail.rating, sharedPreferences.getUserID()!!, isLiked)
                } else {
                    txtvNumberLike.text = (txtvNumberLike.text.toString().toInt() + 1).toString()
                    imgLike.setImageResource(R.drawable.like_green_24)
                    viewModel.updateRating(ratingDetail.rating, sharedPreferences.getUserID()!!, isLiked)
                }
                isLiked = !isLiked
            }

        }
    }

    override fun bindData() {
        binding.apply {
            loadDataAgain()

            binding.apply {
                txtvContentEvaluate.text = ratingDetail.rating.content

                txtvContentEvaluate.text = ratingDetail.rating.content
                txtvNumberStar.text = ratingDetail.rating.numberRating.toString() + "/5"
                txtvNumberLike.text = ratingDetail.rating.likes.size.toString()
                txtvNumberComment.text = ratingDetail.rating.comments.size.toString()
                txtvDate.text = ratingDetail.rating.date
                Glide.with(requireView()).load(ratingDetail.user.imageUser).into(imgUser)
                txtvNameUser.text = ratingDetail.user.userName

            }
        }
    }

    private fun commentClick(userName: String){
        binding.imgSend.visibility = View.VISIBLE
        binding.edtCommnet.setText("@$userName ")
        showKeyboard(requireContext())
    }
    private fun followUser(friendID: String){
        viewModel.addFriends(sharedPreferences.getUserID()!!, friendID)
        viewModel.getAddFriendResult.observe(viewLifecycleOwner){
            Toast.makeText(context, "Theo dõi thành công", Toast.LENGTH_SHORT).show()
        }
    }
    private fun deleteComment(commentDetailID: String){
        viewModel.deleteComment(commentDetailID , ratingDetail.rating)
        viewModel.getDeleCommentResult.observe(viewLifecycleOwner){
            Toast.makeText(context, "Xóa bình luận thành công", Toast.LENGTH_SHORT).show()
            viewModel.getComment(ratingDetail.rating)
            loadDataAgain()
        }
    }
    private fun loadDataAgain(){
        binding.apply {
            viewModel.getCommentResult.observe(viewLifecycleOwner){
                recyclerviewListComment.layoutManager = LinearLayoutManager(binding.root.context)
                listAdapterComment.submitList(it)
                recyclerviewListComment.adapter = listAdapterComment
            }
        }
    }
    private fun clickLike(commentDetail: CommentDetail, isLike: Boolean){
        viewModel.updateComment(commentDetail, sharedPreferences.getUserID()!!, isLike)
        viewModel.getUpdateCommentResult.observe(viewLifecycleOwner){
            Toast.makeText(context, "Đã thích thành công", Toast.LENGTH_SHORT).show()
        }
    }
    private fun menuDotNoDelete(context: Context, view: View, user: User){
        val popupMenu = PopupMenu(context, view)
        popupMenu.inflate(R.menu.menu_dots_evaluate_no_delete)

        popupMenu.setOnMenuItemClickListener { itemMenu ->
            when(itemMenu.itemId){
                R.id.item_report ->{
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }
    private fun menuDot(context: Context, view: View, user: User, rating: Rating){
        val popupMenu = PopupMenu(context, view)
        popupMenu.inflate(R.menu.menu_dots_evaluate)

        popupMenu.setOnMenuItemClickListener { itemMenu ->
            when(itemMenu.itemId){
                R.id.item_report ->{
                    true
                }
                R.id.item_delete ->{
                    viewModel.deleteRating(rating)
                    viewModel.getDeleteRating.observe(viewLifecycleOwner){
                        Toast.makeText(context, "Xóa đánh giá thành công", Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                    }
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }
    private fun showPopupMenuFollow(context: Context, view: View, user: User){
        val popupMenu = PopupMenu(context, view)
        popupMenu.inflate(R.menu.menu_follow)

        popupMenu.setOnMenuItemClickListener { itemMenu ->
            when(itemMenu.itemId){
                R.id.item_follow ->{
                    followUser(user.userID)
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }
    override fun destroy() {

    }
}