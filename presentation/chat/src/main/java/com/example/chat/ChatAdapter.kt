package com.example.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chat.databinding.HolderChatBinding
import com.example.model.Chat

class ChatAdapter(
    private val eventListener: ChatActionHandler
) : ListAdapter<Chat, ChatAdapter.ViewHolder>(EditBookmarkItemDiffCallback){

    init { setHasStableIds(true) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewDataBinding: HolderChatBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.holder_chat,
            parent,
            false
        )
        viewDataBinding.eventListener = eventListener
        return ViewHolder(viewDataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: HolderChatBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Chat) {
            binding.holder = item
            binding.executePendingBindings()
        }
    }

    internal object EditBookmarkItemDiffCallback : DiffUtil.ItemCallback<Chat>() {
        override fun areItemsTheSame(oldItem: Chat, newItem: Chat) =
            oldItem.chatId == newItem.chatId

        override fun areContentsTheSame(oldItem: Chat, newItem: Chat) =
            oldItem == newItem
    }
}