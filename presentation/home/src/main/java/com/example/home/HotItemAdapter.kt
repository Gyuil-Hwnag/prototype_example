package com.example.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.home.databinding.HolderCategoryBinding
import com.example.home.databinding.HolderHotItemBinding
import com.example.home.databinding.HolderRecommendedBinding
import com.example.model.Category
import com.example.model.HotItem
import com.example.model.Recommended

class HotItemAdapter(
    private val eventListener: HomeActionHandler
) : ListAdapter<HotItem, HotItemAdapter.ViewHolder>(HotsItemDiffCallback){

    init { setHasStableIds(true) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewDataBinding: HolderHotItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.holder_hot_item,
            parent,
            false
        )
        viewDataBinding.eventListener = eventListener
        return ViewHolder(viewDataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: HolderHotItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HotItem) {
            binding.holder = item
            binding.executePendingBindings()
        }
    }

    internal object HotsItemDiffCallback : DiffUtil.ItemCallback<HotItem>() {
        override fun areItemsTheSame(oldItem: HotItem, newItem: HotItem) =
            oldItem.hotId == newItem.hotId

        override fun areContentsTheSame(oldItem: HotItem, newItem: HotItem) =
            oldItem == newItem
    }
}