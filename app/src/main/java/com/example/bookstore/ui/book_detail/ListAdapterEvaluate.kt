package com.example.bookstore.ui.book_detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import com.example.bookstore.R
import com.example.bookstore.base.BaseAdapter
import com.example.bookstore.base.BaseViewHolder
import com.example.bookstore.databinding.ItemBookEvaluateBinding
import com.example.bookstore.models.Evaluate

class ListAdapterEvaluate: BaseAdapter<Evaluate, BaseViewHolder<Evaluate>>(Evaluate.differUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Evaluate> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBookEvaluateBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(val binding: ItemBookEvaluateBinding) :
        BaseViewHolder<Evaluate>(binding) {
        @SuppressLint("SetTextI18n")
        override fun bindView(item: Evaluate, isItemSelected: Boolean) {
            super.bindView(item, isItemSelected)
            var isLiked: Boolean = false
            binding.apply {
                txtvContentEvaluate.text = item.content
                imgDots.setOnClickListener {
                    val popupMenu = PopupMenu(root.context, it)
                    popupMenu.inflate(R.menu.menu_dots_evaluate)
                    popupMenu.show()
                }
                imgLike.setOnClickListener {
                    if(isLiked){
                        isLiked = false
                        txtvNumberLike.text = (txtvNumberLike.text.toString().toInt() + 1).toString()
                        imgLike.setImageResource(R.drawable.like_green_24)
                    }else{
                        isLiked = true
                        if (imgLike.drawable.constantState?.equals(ContextCompat.getDrawable(root.context,
                                R.drawable.like_green_24)?.constantState) == true) {
                            txtvNumberLike.text = (txtvNumberLike.text.toString().toInt() -1).toString()
                        }
                        imgLike.setImageResource(R.drawable.like_grey_24)
                    }
                }
            }
        }
    }
}