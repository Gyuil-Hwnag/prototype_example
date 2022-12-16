package com.example.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.home.databinding.HolderCategoryBinding
import com.example.home.databinding.HolderRecommendedBinding
import com.example.model.Category
import com.example.model.Recommended

class RecommededAdapter(
    private val eventListener: HomeActionHandler
) : ListAdapter<Recommended, RecommededAdapter.ViewHolder>(HotsItemDiffCallback){

    init { setHasStableIds(true) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewDataBinding: HolderRecommendedBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.holder_recommended,
            parent,
            false
        )
        viewDataBinding.eventListener = eventListener
        return ViewHolder(viewDataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: HolderRecommendedBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Recommended) {
            binding.holder = item
            binding.executePendingBindings()
        }
    }

    internal object HotsItemDiffCallback : DiffUtil.ItemCallback<Recommended>() {
        override fun areItemsTheSame(oldItem: Recommended, newItem: Recommended) =
            oldItem.recommendId == newItem.recommendId

        override fun areContentsTheSame(oldItem: Recommended, newItem: Recommended) =
            oldItem == newItem
    }
}