package com.equinoxlab.annapurna.framework.utils.multi_selection_drop_down

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.manohar.useful_views.R
import com.manohar.useful_views.adapter.single_multi_selector.SelectionModel


class SelectionViewAdapter: RecyclerView.Adapter<SelectionViewAdapter.MyViewHolder>() {
    var list = arrayListOf<SelectionModel>()
    private var selectionViewHandler: SelectionViewHandler? = null

    fun attachListener(selectionViewHandler: SelectionViewHandler) {
        this.selectionViewHandler = selectionViewHandler
    }

    fun updateData(list: ArrayList<SelectionModel>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.selectedTitle)
        val clear = itemView.findViewById<ImageView>(R.id.clear)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_services, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = list[position]
        holder.title.text = "${position.plus(1)}. ${current.data?.name?: ""}"
        holder.clear.setOnClickListener {
            selectionViewHandler?.action(current, position)
        }
    }


}

interface SelectionViewHandler {
    fun action(item: SelectionModel, position: Int)
}