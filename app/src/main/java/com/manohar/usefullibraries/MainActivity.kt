package com.manohar.usefullibraries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.manohar.useful_views.adapter.filters.Category
import com.manohar.useful_views.adapter.single_multi_selector.SearchModel
import com.manohar.useful_views.adapter.single_multi_selector.SelectionModel
import com.manohar.useful_views.views.FilterBottomSheetDialog
import com.manohar.useful_views.views.MultiSelectorDialog
import com.manohar.useful_views.views.SelectorDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = arrayListOf<SelectionModel>()
        list.add(SelectionModel(data = SearchModel("1", "Apples"), isSelected = false))
        list.add(SelectionModel(data = SearchModel("2", "Grapes"), isSelected = false))
        list.add(SelectionModel(data = SearchModel("3", "Oranges"), isSelected = false))
        list.add(SelectionModel(data = SearchModel("4", "Pineapples"), isSelected = false))


        /* SelectorDialog.show("Choose your favourite fruits", this, list) { selectedItems->
             //Selected Items List
             Toast.makeText(this, selectedItems.toString(), Toast.LENGTH_SHORT).show()

         }.show()

         MultiSelectorDialog.show("Choose your favourite fruits", this, list) { selectedItems->
             //Selected Items List
             Toast.makeText(this, selectedItems.toString(), Toast.LENGTH_SHORT).show()

         }.show()*/


        val list2 = arrayListOf<Category>()

        list2.add(
            Category(
                catID = "1.1",
                catName = "Fruits Fruits Fruits FruitsFruits FruitsFruits",
                isCatHovered = false,
                isCatSelected = false,
                filters = arrayListOf<SelectionModel>(
                    SelectionModel(data = SearchModel("1", "Apples"), isSelected = false),
                    SelectionModel(data = SearchModel("2", "Grapes"), isSelected = false),
                    SelectionModel(data = SearchModel("3", "Grapes 2"), isSelected = false),
                    SelectionModel(data = SearchModel("4", "Grapes 3"), isSelected = false)
                )
            )
        )

        list2.add(
            Category(
                catID = "1.2",
                catName = "Flowers",
                isCatHovered = false,
                isCatSelected = false,
                filters = arrayListOf<SelectionModel>(
                    SelectionModel(data = SearchModel("5", "Mogra"), isSelected = false),
                    SelectionModel(data = SearchModel("6", "Chafa"), isSelected = false),
                    SelectionModel(data = SearchModel("7", "Lili"), isSelected = false),
                    SelectionModel(data = SearchModel("8", "Rose 22"), isSelected = false)
                ),
                isSingleSelection =  true
            )
        )


        FilterBottomSheetDialog.show("Filters", this, list2){
            Log.i("Filters", it.toString())
        }.show()


    }
}