package com.manohar.usefullibraries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.manohar.useful_views.adapter.filters.Category
import com.manohar.useful_views.adapter.single_multi_selector.SearchModel
import com.manohar.useful_views.adapter.single_multi_selector.SelectionModel
import com.manohar.useful_views.views.FilterBottomSheetDialog
import com.manohar.useful_views.views.MultiSelectorDialog
import com.manohar.useful_views.views.SelectorDialog
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    var list2 = arrayListOf<Category?>()

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




        list2.add(
            Category(
                catID = "1.1",
                catName = "Fruits Fruits Fruits FruitsFruits FruitsFruits",
                isCatHovered = false,
                isCatSelected = false,
                filters = arrayListOf<SelectionModel>(
                    SelectionModel(data = SearchModel("1", "Apples"), isSelected = false, catID = "1.1"),
                    SelectionModel(data = SearchModel("2", "Grapes"), isSelected = false, catID = "1.1"),
                    SelectionModel(data = SearchModel("3", "Grapes 2"), isSelected = false, catID = "1.1"),
                    SelectionModel(data = SearchModel("4", "Grapes 3"), isSelected = false, catID = "1.1")
                ),
                isSingleSelection = false
            )
        )

        list2.add(
            Category(
                catID = "1.2",
                catName = "Flowers",
                isCatHovered = false,
                isCatSelected = false,
                filters = arrayListOf<SelectionModel>(
                    SelectionModel(data = SearchModel("5", "Mogra"), isSelected = false, catID = "1.2"),
                    SelectionModel(data = SearchModel("6", "Chafa"), isSelected = false, catID = "1.2"),
                    SelectionModel(data = SearchModel("7", "Lili"), isSelected = false, catID = "1.2"),
                    SelectionModel(data = SearchModel("8", "Rose 22"), isSelected = false, catID = "1.2")
                ),
                isSingleSelection =  true
            )
        )


        val textview = findViewById<TextView>(R.id.textview)
        textview.setOnClickListener {
            showDialog(list2)
        }



    }


    private fun showDialog(list: java.util.ArrayList<Category?>)
    {
        FilterBottomSheetDialog.show("Filters", this, list){
            list2 = ArrayList(it)
        }.show()
    }


}