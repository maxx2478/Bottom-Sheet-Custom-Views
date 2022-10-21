package com.manohar.useful_views.adapter.filters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.manohar.useful_views.adapter.single_multi_selector.SelectionModel
import com.manohar.useful_views.databinding.ItemSelectorBinding
import com.manohar.useful_views.views.FilterBottomSheetDialog

class FilterAdapter(var onMarked: ((SelectionModel?, Boolean) -> Unit)? = null) :
    ListAdapter<SelectionModel, FilterAdapter.ViewHolder>(DiffCallback()) {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSelectorBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem, onMarked)
        Log.i("categoryAdapte", currentItem.toString())

    }

    inner class ViewHolder(private val binding: ItemSelectorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(current: SelectionModel, onMarked: ((SelectionModel?, Boolean) -> Unit)?) {
            binding.title.text = current.data?.name ?: ""
            binding.selected.isChecked = current.isSelected
            binding.selected.setOnClickListener {
                onMarked?.invoke(current, binding.selected.isChecked)
            }
        }

    }

    class DiffCallback : DiffUtil.ItemCallback<SelectionModel>() {
        override fun areItemsTheSame(oldItem: SelectionModel, newItem: SelectionModel) =
            oldItem.data == newItem.data

        override fun areContentsTheSame(oldItem: SelectionModel, newItem: SelectionModel) =
            oldItem == newItem
    }
}

