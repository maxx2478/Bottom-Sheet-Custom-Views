package com.manohar.useful_views.adapter.single_multi_selector

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.manohar.useful_views.databinding.ItemSelectorBinding

data class SelectionModel(
    val data: SearchModel?,
    var isSelected: Boolean
)

data class SearchModel(val id: String, val name: String)

class SelectionAdapter(var onMarked: ((SelectionModel?, Boolean) -> Unit)? = null) :
    RecyclerView.Adapter<SelectionAdapter.MyViewHolder>() {
    var list = arrayListOf<SelectionModel>()
    private var isMultiSelection = true

    fun isMultiSelectionMode(isMultiSelection: Boolean) {
        this.isMultiSelection = isMultiSelection
    }


    fun updateData(list: ArrayList<SelectionModel>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemSelectorBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position], onMarked, isMultiSelection)


    }

    class MyViewHolder(var binding: ItemSelectorBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            current: SelectionModel,
            onMarked: ((SelectionModel?, Boolean) -> Unit)?,
            isMultiSelection: Boolean
        )
        {
            binding.title.text = current.data?.name ?: ""
            binding.selected.isVisible = isMultiSelection
            binding.selected.isChecked = current.isSelected
            binding.selected.setOnClickListener {
                //selectionHandler?.action(holder.select.isChecked, current)
                onMarked?.invoke(current, binding.selected.isChecked)
            }

            if (!isMultiSelection)
                binding.title.setOnClickListener {
                    onMarked?.invoke(current, binding.selected.isChecked)
                }
        }

    }


}

