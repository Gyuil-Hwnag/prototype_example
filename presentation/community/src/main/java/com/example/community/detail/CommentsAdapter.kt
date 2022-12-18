package com.example.community.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.commnunity.R
import com.example.commnunity.databinding.HolderCommentsBinding
import com.example.model.Comments

class CommentsAdapter(
) : ListAdapter<Comments, CommentsAdapter.ViewHolder>(CommentsDiffCallback){

    init { setHasStableIds(true) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewDataBinding: HolderCommentsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.holder_comments,
            parent,
            false
        )
        return ViewHolder(viewDataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: HolderCommentsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Comments) {
            binding.holder = item
            binding.executePendingBindings()
        }
    }

    internal object CommentsDiffCallback : DiffUtil.ItemCallback<Comments>() {
        override fun areItemsTheSame(oldItem: Comments, newItem: Comments) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Comments, newItem: Comments) =
            oldItem == newItem
    }
}