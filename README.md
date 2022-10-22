# Bottom Sheet Custom Views

How to implement?
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
add dependencies:
```
dependencies {
	implementation 'com.github.maxx2478:Bottom-Sheet-Custom-Views:v1.01'
	}
  ```
  
**1) Show a Filter Bottom Sheet (inspired by flipkart)**

![image](https://user-images.githubusercontent.com/64951609/197324236-6f8a8646-2d4a-4fb7-b966-8d9579d33d56.png)

```
var originalList = arrayListOf<Category?>()
originalList.add(
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
                isSingleSelection = false //choose mode : true-> single selection, false -> many
            )
        )
// add more items in the list

//Show the bottom sheet
FilterBottomSheetDialog.show("Filters", this, originalList){ it->
        //List of all items (including selected and not selected)
        originalList = ArrayList(it) //update your list
	filterUIItems(originalList)
        }.show()
```
  
 **2) Show a Multi Selector Bottom Sheet Dialog**
  ```
        val list = arrayListOf<SelectionModel>()
        list.add(SelectionModel(data = SearchModel("1", "Apples"), isSelected = false))
        list.add(SelectionModel(data = SearchModel("2", "Grapes"), isSelected = false))
        //add as much as you want
	
        //Show the bottom sheet
        MultiSelectorDialog.show("Choose your favourite fruits", this, list) { selectedItems->
            //Selected Items List
            Toast.makeText(this, selectedItems.toString(), Toast.LENGTH_SHORT).show()

        }.show()

  ```
![image](https://user-images.githubusercontent.com/64951609/196044350-3bdd920b-29c5-40f5-ad8a-1bd7e9e9f999.png)


**3) Show a Single Selection Bottom Sheet**
  ```
        val list = arrayListOf<SelectionModel>()
        list.add(SelectionModel(data = SearchModel("1", "Apples"), isSelected = false))
        list.add(SelectionModel(data = SearchModel("2", "Grapes"), isSelected = false))
        //add as much as you want

        //Show the bottom sheet
        SelectorDialog.show("Choose your favourite fruits", this, list) { selectedItem->
            //Selected Item 
            Toast.makeText(this, selectedItem.toString(), Toast.LENGTH_SHORT).show()

        }.show()

  ```
  
