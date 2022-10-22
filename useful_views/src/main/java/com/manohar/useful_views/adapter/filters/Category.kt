package com.manohar.useful_views.adapter.filters

import com.manohar.useful_views.adapter.single_multi_selector.SelectionModel

data class Category(
    val catID: String?,
    val catName: String?,
    var isCatHovered: Boolean?,
    var isCatSelected: Boolean?,
    var filters : ArrayList<SelectionModel>,
    var isSingleSelection : Boolean? = false
)
