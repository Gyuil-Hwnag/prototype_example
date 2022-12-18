package com.example.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.category.databinding.HolderCategoryListBinding
import com.example.model.Category
import com.example.model.HotItem
import com.example.model.Recommended

class CategoryListAdapter(
    private val eventListener: CategoryActionHandler
) : ListAdapter<HotItem, CategoryListAdapter.ViewHolder>(HotsItemDiffCallback){

    init { setHasStableIds(true) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewDataBinding: HolderCategoryListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.holder_category_list,
            parent,
            false
        )
        viewDataBinding.eventListener = eventListener
        return ViewHolder(viewDataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: HolderCategoryListBinding) :
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