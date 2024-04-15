package com.example.bookstore.ui.book_detail

import android.annotation.SuppressLint
import android.content.Context
import android.os.CountDownTimer
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.bookstore.R
import com.example.bookstore.base.BaseAdapter
import com.example.bookstore.base.BaseViewHolder
import com.example.bookstore.databinding.ItemBookEvaluateBinding
import com.example.bookstore.models.Evaluate
import com.example.bookstore.models.Rating
import com.example.bookstore.models.RatingDetail
import com.example.bookstore.models.User
import java.util.Timer
import java.util.TimerTask

class ListAdapterEvaluate(private val commentClick: (Rating, String) -> Unit,
                          private val showComment: (RatingDetail) -> Unit,
                          private val followUser: (String) -> Unit,
                          private val clickLike: (Rating, Boolean) -> Unit,
                          private val deleteRating: (Rating) -> Unit,
                          private val userID: String)
    : BaseAdapter<RatingDetail, BaseViewHolder<RatingDetail>>(RatingDetail.differUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<RatingDetail> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBookEvaluateBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(val binding: ItemBookEvaluateBinding) :
        BaseViewHolder<RatingDetail>(binding) {
        @SuppressLint("SetTextI18n")
        override fun bindView(item: RatingDetail, isItemSelected: Boolean) {
            super.bindView(item, isItemSelected)
            var isLiked = item.rating.likes.contains(userID)
            binding.apply {
                txtvContentEvaluate.text = item.rating.content

                txtvContentEvaluate.text = item.rating.content
                txtvNumberStar.text = item.rating.numberRating.toString() + "/5"
                txtvNumberLike.text = item.rating.likes.size.toString()
                if(item.rating.comments.isNotEmpty()){
                    txtvNumberComment.text = item.rating.comments.size.toString() + " phản hồi"
                }else{
                    txtvNumberComment.visibility = View.GONE
                }
                txtvDate.text = item.rating.date

                if(isLiked){
                    imgLike.setImageResource(R.drawable.like_green_24)
                }
                imgLike.setOnClickListener {
                    if (isLiked) {
                        txtvNumberLike.text = (txtvNumberLike.text.toString().toInt() - 1).toString()
                        imgLike.setImageResource(R.drawable.like_grey_24)
                        clickLike(item.rating, false)
                        item.rating.likes.remove(userID)
                    } else {
                        txtvNumberLike.text = (txtvNumberLike.text.toString().toInt() + 1).toString()
                        imgLike.setImageResource(R.drawable.like_green_24)
                        clickLike(item.rating, true)
                        item.rating.likes.add(userID)
                    }
                    isLiked = !isLiked
                }
                imgComment.setOnClickListener {
                    commentClick(item.rating, binding.txtvNameUser.text.toString())
                }
                txtvNumberComment.setOnClickListener {
                    showComment(item)
                }
                Glide.with(root.rootView).load(item.user.imageUser).into(imgUser)
                txtvNameUser.text = item.user.userName
                if(userID == item.user.userID){
                    imgDots.setOnClickListener{
                        menuDot(root.context, it, item.user, item.rating)
                    }
                }else{
                    imgUser.setOnClickListener {
                        showPopupMenuFollow(root.context, it, item.user)
                    }
                    txtvNameUser.setOnClickListener {
                        showPopupMenuFollow(root.context, it, item.user)
                    }
                    imgDots.setOnClickListener{
                        menuDotNoDelete(root.context, it, item.user)
                    }
                }
            }
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
                        deleteRating(rating)
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }
    }
}