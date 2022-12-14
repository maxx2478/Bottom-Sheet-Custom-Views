package com.manohar.useful_views.views

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.manohar.useful_views.R
import com.manohar.useful_views.adapter.single_multi_selector.SelectionAdapter
import com.manohar.useful_views.adapter.single_multi_selector.SelectionModel


class MultiSelectorDialog() {

    companion object{
        lateinit var selectionAdapter: SelectionAdapter


        fun show(title: String, context: Context, RealLists: ArrayList<SelectionModel>, onSubmit: ((List<SelectionModel?>) -> Unit)? = null) : BottomSheetDialog {
            val list = RealLists
            val sheet = BottomSheetDialog(context)

            val factory = LayoutInflater.from(context)
            val dialogView: View =
                factory.inflate(R.layout.dialog_selector, null)
            sheet.setContentView(dialogView)

            val mBottomSheetBehaviorCallback: BottomSheetCallback =
                object : BottomSheetCallback() {
                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                        if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                            sheet.dismiss()
                        }
                    }

                    override fun onSlide(bottomSheet: View, slideOffset: Float) {}
                }

            sheet.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
            val params =
                (dialogView.getParent() as View).layoutParams as CoordinatorLayout.LayoutParams
            val behavior = params.behavior




            if (behavior != null && behavior is BottomSheetBehavior<*>) {
                behavior.setBottomSheetCallback(mBottomSheetBehaviorCallback)
                (behavior as BottomSheetBehavior<*>).state = BottomSheetBehavior.STATE_EXPANDED
            }

            /*val dialog = AlertDialog.Builder(context).create()
            dialog.setCancelable(false)
            dialog.setView(dialogView)
            dialog.setCanceledOnTouchOutside(false)
            dialog.window?.setGravity(Gravity.BOTTOM)*/


            //dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));


            val selectorRV: RecyclerView? =
                dialogView.findViewById<RecyclerView>(R.id.selectorRV)

            val done: MaterialButton? =
                dialogView.findViewById<MaterialButton>(R.id.done)

            val search: TextInputEditText? =
                dialogView.findViewById<TextInputEditText>(R.id.search_bar)


            val titleSheet : TextView? =
                dialogView.findViewById<TextView>(R.id.title)

            titleSheet?.text = title


            selectionAdapter = SelectionAdapter()
            selectorRV?.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            selectorRV?.adapter = selectionAdapter


            selectionAdapter.updateData(list)
            selectionAdapter.onMarked = { item, isChecked ->
                list.find { list -> list.data?.equals(item?.data) == true }?.isSelected = isChecked
            }


            done?.setOnClickListener {
                sheet.dismiss()
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



            return sheet
        }



    }




}