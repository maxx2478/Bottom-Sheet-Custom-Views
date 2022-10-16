# multi selector bottom sheet library

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
	 implementation 'com.github.maxx2478:multi_selector_dialog_library:1.1'
	}
  ```
  How to show Bottom Sheet Dialog?
  ```
        val list = arrayListOf<SelectionModel>()
        list.add(SelectionModel(data = SearchModel("1", "Apples"), isSelected = false))
        list.add(SelectionModel(data = SearchModel("2", "Grapes"), isSelected = false))
        //add as much as you want

        SelectorDialog.showSelectorDialog("Choose your favourite fruits", this, list) { selectedItems->
            //Selected Items List
            Toast.makeText(this, selectedItems.toString(), Toast.LENGTH_SHORT).show()

        }.show()

  ```
![image](https://user-images.githubusercontent.com/64951609/196044350-3bdd920b-29c5-40f5-ad8a-1bd7e9e9f999.png)
![image](https://user-images.githubusercontent.com/64951609/196044374-2cafa511-0cd5-4bad-a40a-64ab1df16b6a.png)

