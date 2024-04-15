package com.example.bookstore.ui.book_detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import com.example.bookstore.R
import com.example.bookstore.base.BaseAdapter
import com.example.bookstore.base.BaseViewHolder
import com.example.bookstore.databinding.ItemBookEvaluateBinding
import com.example.bookstore.databinding.ItemRatingBarBinding
import com.example.bookstore.models.Evaluate

class ListAdapterRatingMain: BaseAdapter<Double,
        BaseViewHolder<Double>>(
    object : DiffUtil.ItemCallback<Double>() {
        override fun areItemsTheSame(oldItem: Double, newItem: Double): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Double, newItem: Double): Boolean {
            return oldItem == newItem
        }
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Double> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRatingBarBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
    var index = 1
    inner class ViewHolder(val binding: ItemRatingBarBinding) :
        BaseViewHolder<Double>(binding) {
        override fun bindView(item: Double, isItemSelected: Boolean) {
            super.bindView(item, isItemSelected)
            binding.apply {
                val layoutParamsTop = layoutRatingTop.layoutParams as ConstraintLayout.LayoutParams
                var ratio = item/100.0
                if(item<10.0){
                    ratio += 0.07
                }
                layoutParamsTop.matchConstraintPercentWidth = ratio.toFloat()
                layoutRatingTop.layoutParams = layoutParamsTop
                textView46.text = index.toString()
                index++
            }
        }
    }
}