package com.example.bookstore.ui.friends

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.bookstore.R
import com.example.bookstore.base.BaseAdapter
import com.example.bookstore.base.BaseViewHolder
import com.example.bookstore.databinding.ItemEvaluateNewFeedBinding
import com.example.bookstore.models.Book
import com.example.bookstore.models.Evaluate
import com.example.bookstore.models.FriendEvaluation
import com.example.bookstore.models.Rating

class ListAdapterFriendsEvaluate(private val imgOnClick: (Book) -> Unit,
                                 private val clickLike: (Rating, Boolean) -> Unit,
                                 private val clickCmt: (FriendEvaluation) -> Unit,
                                 private val userID: String)
    : BaseAdapter<FriendEvaluation, BaseViewHolder<FriendEvaluation>>(FriendEvaluation.differUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<FriendEvaluation> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemEvaluateNewFeedBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(val binding: ItemEvaluateNewFeedBinding) :
        BaseViewHolder<FriendEvaluation>(binding) {
        @SuppressLint("SetTextI18n")
        override fun bindView(item: FriendEvaluation, isItemSelected: Boolean) {
            super.bindView(item, isItemSelected)
            var isLiked = item.rating.likes.contains(userID)
            binding.apply {
                txtvContentEvaluate.text = item.rating.content
                imgDots.setOnClickListener {
                    val popupMenu = PopupMenu(root.context, it)
                    popupMenu.inflate(R.menu.menu_dots_evaluate)
                    popupMenu.show()
                }
                txtvContentEvaluate.text = item.rating.content
                txtvNumberStar.text = item.rating.numberRating.toString() + "/5"
                txtvNumberLike.text = item.rating.likes.size.toString()
                txtvNumberComment.text = item.rating.comments.size.toString()
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
                imgCmt.setOnClickListener {
                    clickCmt(item)
                }
                Glide.with(root.context).load(item.book.images[0]).into(imgBook)
                Glide.with(root.context).load(item.user.imageUser).into(imgUser)
                txtvNameUser.text = item.user.userName
                txtvNameBook.text = item.book.title
                txtvAuthor.text = "Tác giả: " + item.book.author
                imgBook.setOnClickListener {
                    imgOnClick(item.book)
                }
            }

        }
    }
}