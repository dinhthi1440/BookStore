package com.example.bookstore.base

import android.view.View
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.text.DecimalFormat
import java.util.concurrent.Executors


abstract class BaseAdapter<T, VH: BaseViewHolder<T>>(diffUtil: DiffUtil.ItemCallback<T>):
ListAdapter<T, VH>(
    AsyncDifferConfig.Builder(diffUtil)
        .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
        .build()
){
    private var recyclerView: RecyclerView? = null
    private var layoutNoData: View? = null
    private var enableShowNoDataUI = false
    protected val decimalFormat = DecimalFormat("#,###.###")

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bindView(getItem(position), true)
    }

    open fun enableShowNoDataUI(layoutNoData: View) {
        enableShowNoDataUI = true
        this.layoutNoData = layoutNoData
    }

    override fun submitList(list: List<T>?) {
        super.submitList(list)
    }
    fun loadMore(recyclerView: RecyclerView?, handle: () -> Unit){
        recyclerView?.apply {
            addOnScrollListener(
                object: RecyclerView.OnScrollListener(){
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                        val sizeData = recyclerView.adapter?.itemCount?.minus(1)
                        if(
                            linearLayoutManager != null &&
                            linearLayoutManager.findLastCompletelyVisibleItemPosition() == sizeData &&
                            sizeData != -1
                        ){
                            handle()
                        }
                    }
                }
            )
        }
    }
}