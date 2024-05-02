package com.example.bookstore.ui.chatting

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstore.R
import com.example.bookstore.models.Messages

class ListAdapterMessage(private val userID: String) : RecyclerView.Adapter<MessageHolder>() {

    private var listOfMessage = listOf<Messages>()

    private val LEFT = 0
    private val RIGHT = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == RIGHT) {
            val view = inflater.inflate(R.layout.item_chat_right, parent, false)
            MessageHolder(view)
        } else {
            val view = inflater.inflate(R.layout.item_chat_left, parent, false)
            MessageHolder(view)
        }
    }
    override fun getItemCount() = listOfMessage.size
    override fun onBindViewHolder(holder: MessageHolder, position: Int) {
        val message = listOfMessage[position]
        holder.messageText.visibility = View.VISIBLE
        holder.timeOfSent.visibility = View.VISIBLE
        holder.messageText.text = message.message
        holder.timeOfSent.text = "${message.date} LÚC ${message.time?.substring(0, 5)} "?: ""

    }

    override fun getItemViewType(position: Int) =
        if (listOfMessage[position].sender == userID) RIGHT else LEFT

    fun setList(newList: List<Messages>) {

        this.listOfMessage = newList

    }

}

class MessageHolder(itemView: View) : RecyclerView.ViewHolder(itemView.rootView) {
    val messageText: TextView = itemView.findViewById(R.id.txtv_show_mess)
    val timeOfSent: TextView = itemView.findViewById(R.id.txtv_time_send)
}