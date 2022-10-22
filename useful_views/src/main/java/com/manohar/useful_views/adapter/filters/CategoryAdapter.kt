package com.manohar.useful_views.adapter.filters

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.manohar.useful_views.R
import com.manohar.useful_views.adapter.single_multi_selector.SelectionModel
import com.manohar.useful_views.databinding.ItemCatBinding

class CategoryAdapter(var onClicked: ((Category) -> Unit)? = null) :
    ListAdapter<Category, CategoryAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = com.manohar.useful_views.databinding.ItemCatBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
        Log.i("categoryAdapter", currentItem.toString())

    }

    inner class ViewHolder(private val binding: ItemCatBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(current: Category) {
            binding.title.text = current.catName ?: ""
            binding.title.setOnClickListener {
                onClicked?.invoke(current)
            }
            if (current.isCatHovered == true)
            {
                binding.title.setTextColor(Color.WHITE)
                binding.container.setCardBackgroundColor(binding.root.context.resources.getColor(R.color.grey_dark))
            }
            else
            {
                binding.container.setCardBackgroundColor(binding.root.context.resources.getColor(android.R.color.transparent))
                binding.title.setTextColor(binding.root.context.resources.getColor(R.color.grey_extra_dark))


            }
        }

    }

    class DiffCallback : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category) =
            oldItem.catID == newItem.catID || oldItem.isCatHovered == newItem.isCatHovered

        override fun areContentsTheSame(oldItem: Category, newItem: Category) =
            oldItem == newItem
    }
}