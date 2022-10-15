package com.manohar.useful_views

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class SelectorDialog() {

    companion object{
        lateinit var selectionAdapter: SelectionAdapter


        fun showSelectorDialog(context: Context, RealLists: ArrayList<SelectionModel>,  onSubmit: ((List<SelectionModel?>) -> Unit)? = null) : AlertDialog {
            val list = RealLists
            val factory = LayoutInflater.from(context)
            val deleteDialogView: View =
                factory.inflate(R.layout.dialog_selector, null)
            val dialog = AlertDialog.Builder(context).create()
            dialog.setCancelable(true)
            dialog.setView(deleteDialogView)
            dialog.setCanceledOnTouchOutside(true)


            val selectorRV: RecyclerView? =
                deleteDialogView.findViewById<RecyclerView>(R.id.selectorRV)

            val done: MaterialButton? =
                deleteDialogView.findViewById<MaterialButton>(R.id.done)

            val search: TextInputEditText? =
                deleteDialogView.findViewById<TextInputEditText>(R.id.search_bar)



            selectionAdapter = SelectionAdapter()
            selectorRV?.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            selectorRV?.adapter = selectionAdapter


            selectionAdapter.updateData(list)
            selectionAdapter.onMarked = { item, isChecked ->
                list.find { list -> list.data?.equals(item?.data) == true }?.isSelected = isChecked
            }


            done?.setOnClickListener {
                dialog.dismiss()
                onSubmit?.invoke(list.filter {item-> item.isSelected })
            }


            search?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (!p0.isNullOrEmpty()) {
                        val temp = list.filter {
                            it.data?.name?.contains(
                                p0.toString(),
                                ignoreCase = true
                            ) == true
                        }
                        selectionAdapter.updateData(ArrayList(temp))
                    } else {
                        selectionAdapter.updateData(ArrayList(list))
                    }
                }

                override fun afterTextChanged(p0: Editable?) {

                }

            })

            return dialog
        }
    }




}