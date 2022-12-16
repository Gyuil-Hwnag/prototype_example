package com.example.community

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.commnunity.R
import com.example.commnunity.databinding.HolderCommunityBinding
import com.example.model.Community

class CommunityAdapter(
    private val eventListener: CommunityActionHandler
) : ListAdapter<Community, CommunityAdapter.ViewHolder>(EditBookmarkItemDiffCallback){

    init { setHasStableIds(true) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewDataBinding: HolderCommunityBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.holder_community,
            parent,
            false
        )
        viewDataBinding.eventListener = eventListener
        return ViewHolder(viewDataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: HolderCommunityBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Community) {
            binding.holder = item
            binding.executePendingBindings()
        }
    }

    internal object EditBookmarkItemDiffCallback : DiffUtil.ItemCallback<Community>() {
        override fun areItemsTheSame(oldItem: Community, newItem: Community) =
            oldItem.communityId == newItem.communityId

        override fun areContentsTheSame(oldItem: Community, newItem: Community) =
            oldItem == newItem
    }
}