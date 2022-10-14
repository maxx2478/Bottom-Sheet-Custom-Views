package com.manohar.useful_views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.equinoxlab.annapurna.framework.utils.multi_selection_drop_down.SelectionViewAdapter
import kotlinx.coroutines.NonDisposableHandle.parent

data class SelectionModel(
    val data: SearchModel?,
    var isSelected: Boolean
)

data class SearchModel(val id:String, val name:String)

class SelectionAdapter(var onMarked : ((SelectionModel?, Boolean) -> Unit )? = null) : RecyclerView.Adapter<SelectionAdapter.MyViewHolder>() {
    var list = arrayListOf<SelectionModel>()
    private var selectionHandler: SelectionHandler? = null



    fun attachListener(selectionHandler: SelectionHandler) {
        this.selectionHandler = selectionHandler
    }

    fun updateData(list: ArrayList<SelectionModel>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_selector, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = list[position]
       holder.title.text = current.data?.name?: ""
        holder.select.isChecked = current.isSelected
        holder.select.setOnClickListener {
            //selectionHandler?.action(holder.select.isChecked, current)
            onMarked?.invoke(current, holder.select.isChecked)
        }

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.title)
        val select = itemView.findViewById<CheckBox>(R.id.selected)
    }



}


interface SelectionHandler {
    fun action(checked: Boolean, current: SelectionModel)
}