package com.example.telegram.ui.fragments.single_chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.telegram.R
import com.example.telegram.database.CURRENT_UID
import com.example.telegram.models.CommonModel
import com.example.telegram.utils.TYPE_MESSAGE_IMAGE
import com.example.telegram.utils.TYPE_MESSAGE_TEXT
import com.example.telegram.utils.asTime
import com.example.telegram.utils.downloadAndSetImage
import kotlinx.android.synthetic.main.message_item.view.*

class SingleChatAdapter : RecyclerView.Adapter<SingleChatAdapter.SingleChatHolder>() {

    private var mListMessagesCache = mutableListOf<CommonModel>()

    class SingleChatHolder(view: View) : RecyclerView.ViewHolder(view) {
        //Text
        val blocUserMessage: ConstraintLayout = view.block_user_message
        val chatUserMessage: TextView = view.chat_user_message
        val chatUserMessageTime: TextView = view.chat_user_message_time

        val blocReceivedMessage: ConstraintLayout = view.block_received_message
        val chatReceivedMessage: TextView = view.chat_received_message
        val chatReceivedMessageTime: TextView = view.chat_received_message_time

        //Image
        val blocReceivedImageMessage: ConstraintLayout = view.block_received_image_message
        val blocUserImageMessage: ConstraintLayout = view.block_user_image_message
        val chatUserImage: ImageView = view.chat_user_image
        val chatReceivedImage: ImageView = view.chat_received_image
        val chatUserImageMessageTime: TextView = view.chat_user_image_message_time
        val chatReceivedImageMessageTime: TextView = view.chat_received_image_message_time
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleChatHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false)
        return SingleChatHolder(view)
    }

    override fun getItemCount(): Int = mListMessagesCache.size

    override fun onBindViewHolder(holder: SingleChatHolder, position: Int) {
        when (mListMessagesCache[position].type) {
            TYPE_MESSAGE_TEXT -> drawMessageText(holder, position)
            TYPE_MESSAGE_IMAGE -> drawMessageImage(holder, position)

        }
    }

    private fun drawMessageImage(holder: SingleChatHolder, position: Int) {
        holder.apply {
            blocUserMessage.visibility = View.GONE
            blocReceivedMessage.visibility = View.GONE
        }
        if (mListMessagesCache[position].from == CURRENT_UID) {
            holder.apply {
                blocReceivedImageMessage.visibility = View.GONE
                blocUserImageMessage.visibility = View.VISIBLE
                chatUserImage.downloadAndSetImage(mListMessagesCache[position].imageUrl)
                chatUserImageMessageTime.text =
                    mListMessagesCache[position].timeStamp.toString().asTime()
            }
        } else {
            holder.apply {
                blocReceivedImageMessage.visibility = View.VISIBLE
                blocUserImageMessage.visibility = View.GONE
                chatReceivedImage.downloadAndSetImage(mListMessagesCache[position].imageUrl)
                chatReceivedImageMessageTime.text =
                    mListMessagesCache[position].timeStamp.toString().asTime()
            }
        }
    }

    private fun drawMessageText(holder: SingleChatHolder, position: Int) {
        holder.apply {
            blocReceivedImageMessage.visibility = View.GONE
            blocUserImageMessage.visibility = View.GONE
        }
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

    fun addItemToBottom(
        item: CommonModel,
        onSuccess: () -> Unit
    ) {
        if (!mListMessagesCache.contains(item)) {
            mListMessagesCache.add(item)
            notifyItemInserted(mListMessagesCache.size)
        }
        onSuccess()
    }

    fun addItemToTop(
        item: CommonModel,
        onSuccess: () -> Unit
    ) {

        if (!mListMessagesCache.contains(item)) {
            mListMessagesCache.add(item)
            mListMessagesCache.sortBy { it.timeStamp.toString() }
            notifyItemInserted(0)
        }
        onSuccess()
    }
}


