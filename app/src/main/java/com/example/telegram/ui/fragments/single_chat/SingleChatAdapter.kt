package com.example.telegram.ui.fragments.single_chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.telegram.R
import com.example.telegram.models.CommonModel
import com.example.telegram.database.CURRENT_UID
import com.example.telegram.utils.asTime
import kotlinx.android.synthetic.main.message_item.view.*

class SingleChatAdapter : RecyclerView.Adapter<SingleChatAdapter.SingleChatHolder>() {

    private var mListMessagesCache = emptyList<CommonModel>()

    class SingleChatHolder(view: View) : RecyclerView.ViewHolder(view) {
        val blocUserMessage: ConstraintLayout = view.block_user_message
        val chatUserMessage: TextView = view.chat_user_message
        val chatUserMessageTime: TextView = view.chat_user_message_time

        val blocReceivedMessage: ConstraintLayout = view.block_received_message
        val chatReceivedMessage: TextView = view.chat_received_message
        val chatReceivedMessageTime: TextView = view.chat_received_message_time
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleChatHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false)
        return SingleChatHolder(view)
    }

    override fun getItemCount(): Int = mListMessagesCache.size

    override fun onBindViewHolder(holder: SingleChatHolder, position: Int) {
        if (mListMessagesCache[position].from == CURRENT_UID) {
            holder.apply {
                blocUserMessage.visibility = View.VISIBLE
                blocReceivedMessage.visibility = View.GONE
                chatUserMessage.text = mListMessagesCache[position].text
                chatUserMessageTime.text =
                    mListMessagesCache[position].timeStamp.toString().asTime()
            }
        } else {
            holder.apply {
                blocUserMessage.visibility = View.GONE
                blocReceivedMessage.visibility = View.VISIBLE
                chatReceivedMessage.text = mListMessagesCache[position].text
                chatReceivedMessageTime.text =
                    mListMessagesCache[position].timeStamp.toString().asTime()
            }
        }
    }

    fun setList(list: List<CommonModel>) {
        mListMessagesCache = list
        notifyDataSetChanged()
    }
}


