package com.manohar.usefullibraries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.manohar.useful_views.adapter.SearchModel
import com.manohar.useful_views.adapter.SelectionModel
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


        SelectorDialog.show("Choose your favourite fruits", this, list) { selectedItems->
            //Selected Items List
            Toast.makeText(this, selectedItems.toString(), Toast.LENGTH_SHORT).show()

        }.show()

        MultiSelectorDialog.show("Choose your favourite fruits", this, list) { selectedItems->
            //Selected Items List
            Toast.makeText(this, selectedItems.toString(), Toast.LENGTH_SHORT).show()

        }.show()


    }
}