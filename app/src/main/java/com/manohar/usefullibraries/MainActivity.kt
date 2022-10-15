package com.manohar.usefullibraries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.manohar.useful_views.SearchModel
import com.manohar.useful_views.SelectionModel
import com.manohar.useful_views.SelectorDialog
import java.nio.channels.Selector

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = arrayListOf<SelectionModel>()
        list.add(SelectionModel(data = SearchModel("1", "name1"), isSelected = false))
        list.add(SelectionModel(data = SearchModel("2", "name12"), isSelected = false))
        list.add(SelectionModel(data = SearchModel("3", "name13"), isSelected = false))

        val dialog = SelectorDialog.showSelectorDialog(this, list){
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }.show()




    }
}