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
	        implementation 'com.github.maxx2478:multi_selector_dialog_library:v1.0'
	}
  ```
  How to show Bottom Sheet Dialog?
  ```
   val list = arrayListOf<SelectionModel>()
        list.add(SelectionModel(data = SearchModel("1", "Apples"), isSelected = false))
        list.add(SelectionModel(data = SearchModel("2", "Grapes"), isSelected = false))
        //add as much as you want

        val dialog = SelectorDialog.showSelectorDialog("Choose a fav. fruit" ,this, list){
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }.show()
  ```
![image](https://user-images.githubusercontent.com/64951609/195986744-5cf79c5b-7d0e-449e-87b2-deba759df0bf.png)
![image](https://user-images.githubusercontent.com/64951609/195986761-e0bd771a-2e0d-46bc-8e11-f43109be649f.png)
