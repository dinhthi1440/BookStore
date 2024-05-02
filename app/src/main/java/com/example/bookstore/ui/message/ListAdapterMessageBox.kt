package com.example.bookstore.ui.message

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.bookstore.base.BaseAdapter
import com.example.bookstore.base.BaseViewHolder
import com.example.bookstore.databinding.ItemShopMessageBinding
import com.example.bookstore.models.RecentChats
import com.example.bookstore.util.Utils

class ListAdapterMessageBox(private val onClickItem: (String) -> Unit): BaseAdapter<RecentChats, BaseViewHolder<RecentChats>>(RecentChats.differUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<RecentChats> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemShopMessageBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(val binding: ItemShopMessageBinding) :
        BaseViewHolder<RecentChats>(binding) {
        override fun bindView(item: RecentChats, isItemSelected: Boolean) {
            super.bindView(item, isItemSelected)
            binding.apply {
                Glide.with(root.rootView).load(item.friendsImage).into(imgAvtShop)
                txtvNameShop.text = item.name
                if(item.person=="you"){
                    txtvRecentChat.text = "Bạn: ${item.message}"
                }else{
                    txtvRecentChat.text = item.message
                }
                val minus = Utils.calculateMinutesFrom(item.date + " "+ item.time)
                if(minus <60){
                    txtvTime.text = "$minus phút"
                }else if(minus <1440 ){
                    txtvTime.text = item.time.substring(0, 5)
                }else{
                    txtvTime.text = "${item.date.substring(8,10)}/${item.date.substring(6,8)}"
                }
                root.setOnClickListener {
                    onClickItem(item.friendID)
                }
                txtvNameShop.setOnClickListener {
                    onClickItem(item.friendID)
                }
                txtvRecentChat.setOnClickListener {
                    onClickItem(item.friendID)
                }
                imgAvtShop.setOnClickListener {
                    onClickItem(item.friendID)
                }
            }
        }
    }
}