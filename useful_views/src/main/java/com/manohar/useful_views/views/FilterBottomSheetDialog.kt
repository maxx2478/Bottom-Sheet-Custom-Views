package com.manohar.useful_views.views

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Filter
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.manohar.useful_views.R
import com.manohar.useful_views.adapter.filters.Category
import com.manohar.useful_views.adapter.filters.CategoryAdapter
import com.manohar.useful_views.adapter.filters.FilterAdapter
import com.manohar.useful_views.adapter.single_multi_selector.SelectionAdapter
import com.manohar.useful_views.adapter.single_multi_selector.SelectionModel
import kotlin.random.Random

class FilterBottomSheetDialog {

    companion object {

        lateinit var filterAdapter: FilterAdapter
        lateinit var categoryAdapter: CategoryAdapter
        lateinit var currFilters: List<SelectionModel>

        fun show(
            title: String,
            context: Context,
            list: ArrayList<Category>,
            onSubmit: ((List<Category?>?) -> Unit)? = null
        ): BottomSheetDialog {

            var RealLists = list
            val sheet = BottomSheetDialog(context)

            val factory = LayoutInflater.from(context)
            val dialogView: View =
                factory.inflate(R.layout.filter_bottom_sheet, null)
            sheet.setContentView(dialogView)

            val mBottomSheetBehaviorCallback: BottomSheetBehavior.BottomSheetCallback =
                object : BottomSheetBehavior.BottomSheetCallback() {
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


            val filterRv: RecyclerView =
                dialogView.findViewById<RecyclerView>(R.id.filterRv)

            val filterCatsRV: RecyclerView =
                dialogView.findViewById<RecyclerView>(R.id.filterCatsRV)

            val done: MaterialButton =
                dialogView.findViewById<MaterialButton>(R.id.done)

            val clear: MaterialButton =
                dialogView.findViewById<MaterialButton>(R.id.clear)


            val titleSheet: TextView =
                dialogView.findViewById<TextView>(R.id.title)

            titleSheet.text = title


            filterAdapter = FilterAdapter()
            categoryAdapter = CategoryAdapter()

            filterRv.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = filterAdapter
                itemAnimator = null
            }

            filterCatsRV.apply {
                itemAnimator = null
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = categoryAdapter
            }


            categoryAdapter.submitList(RealLists)

            done.setOnClickListener {
                val temp = categoryAdapter.currentList
                temp.forEach {
                    it.isCatHovered = false
                }
                onSubmit?.invoke(temp)
                sheet.dismiss()
            }

            clear.setOnClickListener {
                val temp = RealLists.map { it.copy() }
                temp.forEach { cate ->
                    cate.isCatHovered = false
                    cate.isCatSelected = false
                    cate.filters.forEach { filter ->
                        filter.isSelected = false
                    }
                }
                onSubmit?.invoke(temp)
                sheet.dismissWithAnimation = true
                sheet.dismiss()
            }

            categoryAdapter.onClicked = { category ->

                val obj = Category(
                    catID = category.catID,
                    catName = category.catName,
                    isCatHovered = category.isCatHovered,
                    isCatSelected = category.isCatSelected,
                    filters = category.filters,
                    isSingleSelection = category.isSingleSelection
                )
                val temp = RealLists.map { list -> list.copy() }
                temp.forEach { item ->
                    item.isCatHovered = item.catID == obj.catID
                }

                RealLists =
                    temp as ArrayList<Category> /* = java.util.ArrayList<com.manohar.useful_views.adapter.filters.Category> */
                val categoryCopy = obj.filters.map { it.copy() }

                categoryAdapter.submitList(temp.toMutableList())
                currFilters = categoryCopy as ArrayList<SelectionModel> /* = java.util.ArrayList<com.manohar.useful_views.adapter.single_multi_selector.SelectionModel> */


                filterAdapter.submitList(categoryCopy.toMutableList())

            }

            filterAdapter.onMarked = { selectionModel: SelectionModel?, b: Boolean ->
                val temp = RealLists.map { it.copy() }
                val temp2 = currFilters.map { it.copy() }

                temp.forEachIndexed { index, it ->
                    it.isCatSelected = b
                    if (it.isSingleSelection == true) {
                        temp2.forEach { filter -> filter.isSelected = false }
                    }

                    temp2.find { item ->
                        item.data?.id == selectionModel?.data?.id
                    }?.isSelected = b

                    if (it.filters[0].data?.id.equals(temp2[0].data?.id))
                    {
                        it.filters =
                            temp2 as ArrayList<SelectionModel> /* = java.util.ArrayList<com.manohar.useful_views.adapter.single_multi_selector.SelectionModel> */

                    }


                }

                currFilters = temp2 as ArrayList<SelectionModel>

                RealLists =
                    temp as ArrayList<Category> /* = java.util.ArrayList<com.manohar.useful_views.adapter.filters.Category> */
                categoryAdapter.submitList(temp.toMutableList())
                filterAdapter.submitList(temp2.toMutableList())

            }



            return sheet
        }


    }


}