package com.example.bookstore.ui.reply_rating

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.bookstore.R
import com.example.bookstore.base.BaseAdapter
import com.example.bookstore.base.BaseViewHolder
import com.example.bookstore.databinding.ItemCommentBinding
import com.example.bookstore.models.Comment
import com.example.bookstore.models.CommentDetail
import com.example.bookstore.models.Rating
import com.example.bookstore.models.User

class ListAdapterComment(private val commentClick: (String) -> Unit,
                         private val followUser: (String) -> Unit,
                         private val deleteComment: (String) -> Unit,
                         private val clickLike: (CommentDetail, Boolean) -> Unit,
                         private val getUID: String)

    : BaseAdapter<Comment, BaseViewHolder<Comment>>(Comment.differUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Comment> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCommentBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(val binding: ItemCommentBinding) :
        BaseViewHolder<Comment>(binding) {
        override fun bindView(item: Comment, isItemSelected: Boolean) {
            super.bindView(item, isItemSelected)
            var isLiked = item.commentDetail.likes.contains(getUID)
            binding.apply {
                txtvContentEvaluate.text = item.commentDetail.content
                imgDots.setOnClickListener {
                    val popupMenu = PopupMenu(root.context, it)
                    popupMenu.inflate(R.menu.menu_dots_evaluate)
                    popupMenu.show()
                }
                txtvContentEvaluate.text = item.commentDetail.content
                txtvNumberLike.text = item.commentDetail.likes.size.toString()
                txtvDate.text = item.commentDetail.date
                if(isLiked){
                    imgLike.setImageResource(R.drawable.like_green_24)
                }
                imgLike.setOnClickListener {
                    if (isLiked) {
                        txtvNumberLike.text = (txtvNumberLike.text.toString().toInt() - 1).toString()
                        imgLike.setImageResource(R.drawable.like_grey_24)
                        clickLike(item.commentDetail, false)
                        item.commentDetail.likes.remove(getUID)
                    } else {
                        txtvNumberLike.text = (txtvNumberLike.text.toString().toInt() + 1).toString()
                        imgLike.setImageResource(R.drawable.like_green_24)
                        clickLike(item.commentDetail, true)
                        item.commentDetail.likes.remove(getUID)
                    }
                    isLiked = !isLiked
                }
                txtvNameUser.text = item.user.userName
                Glide.with(root.rootView).load(item.user.imageUser).into(imgUser)
                imgComment.setOnClickListener {
                    commentClick(item.user.userName)
                }
                imgComment.setOnClickListener {
                    commentClick(binding.txtvNameUser.text.toString())
                }
                if(getUID == item.user.userID){
                    imgDots.setOnClickListener{
                        menuDot(root.context, it, item.user, item)
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
        private fun menuDot(context: Context, view: View, user: User,  comment: Comment){
            val popupMenu = PopupMenu(context, view)
            popupMenu.inflate(R.menu.menu_dots_evaluate)

            popupMenu.setOnMenuItemClickListener { itemMenu ->
                when(itemMenu.itemId){
                    R.id.item_report ->{
                        true
                    }
                    R.id.item_delete ->{
                        deleteComment(comment.commentDetail.id)
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }
    }
}