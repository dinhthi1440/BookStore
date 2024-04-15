package com.example.bookstore.ui.my_friends

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import com.bumptech.glide.Glide
import com.example.bookstore.R
import com.example.bookstore.base.BaseAdapter
import com.example.bookstore.base.BaseViewHolder
import com.example.bookstore.databinding.ItemFriendBinding
import com.example.bookstore.models.User

class ListAdapterFriend (private val onClickItem: (User, String) -> Unit)
    : BaseAdapter<User, BaseViewHolder<User>>(User.differUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<User> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFriendBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(val binding: ItemFriendBinding) :
        BaseViewHolder<User>(binding) {
        override fun bindView(item: User, isItemSelected: Boolean) {
            super.bindView(item, isItemSelected)
            binding.apply {
                Glide.with(root.rootView).load(item.imageUser).into(imgUser)
                txtvUserName.text = item.userName
                txtvBirthDate.text = item.dateOfBirth
                root.setOnClickListener {
                    menuDot(root.context, root, item)
                }
                imgUser.setOnClickListener {
                    menuDot(root.context, root, item)
                }
                txtvUserName.setOnClickListener {
                    menuDot(root.context, root, item)
                }
            }
        }

        private fun menuDot(context: Context, view: View, item: User){
            val popupMenu = PopupMenu(context, view)
            popupMenu.inflate(R.menu.menu_friend)

            popupMenu.setOnMenuItemClickListener { itemMenu ->
                when(itemMenu.itemId){
                    R.id.item_chat ->{
                        onClickItem(item, "chat")
                        true
                    }
                    R.id.item_delete ->{
                        onClickItem(item, "delete")
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }
    }
}