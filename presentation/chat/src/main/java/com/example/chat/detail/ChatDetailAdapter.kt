package com.example.chat.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chat.R
import com.example.chat.databinding.HolderChatMyBinding
import com.example.chat.databinding.HolderChatOtherBinding
import com.example.model.ChatDetail

class ChatDetailAdapter(
) : ListAdapter<ChatDetail, ChatViewHolder>(NotificationItemDiffCallback){

    init { setHasStableIds(true) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return when(viewType) {
            R.layout.holder_chat_my -> {
                ChatViewHolder.ChatMyViewHolder(
                    HolderChatMyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
            else -> {
                ChatViewHolder.ChatOtherViewHolder(
                    HolderChatOtherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
        }
    }


    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun getItemViewType(position: Int): Int =
        when(getItem(position).chatType) {
            1 -> R.layout.holder_chat_my
            else -> R.layout.holder_chat_other
        }

    internal object NotificationItemDiffCallback : DiffUtil.ItemCallback<ChatDetail>() {
        override fun areItemsTheSame(oldItem: ChatDetail, newItem: ChatDetail) =
            oldItem.chatId == newItem.chatId

        override fun areContentsTheSame(oldItem: ChatDetail, newItem: ChatDetail) =
            oldItem == newItem
    }
}

sealed class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    open fun bind(chat: ChatDetail) = Unit

    class ChatMyViewHolder(private val binding: HolderChatMyBinding): ChatViewHolder(binding.root) {

        override fun bind(chat: ChatDetail) {
            binding.holder = chat
            binding.executePendingBindings()
        }
    }

    class ChatOtherViewHolder(private val binding: HolderChatOtherBinding): ChatViewHolder(binding.root) {

        override fun bind(chat: ChatDetail) {
            binding.holder = chat
            binding.executePendingBindings()
        }
    }
}