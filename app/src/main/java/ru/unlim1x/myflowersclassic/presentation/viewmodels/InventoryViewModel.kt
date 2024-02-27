package ru.unlim1x.myflowersclassic.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.unlim1x.myflowersclassic.data.enteties.FlowerInventory
import ru.unlim1x.myflowersclassic.domain.enteties.Tool

class InventoryViewModel : ViewModel() {
    val listOfTools : MutableLiveData<MutableList<Tool>> = MutableLiveData()
    val listOfInventoryFlowers : MutableLiveData<MutableList<FlowerInventory>> = MutableLiveData()

    fun loadToolsManual(){
        val list = mutableListOf<Tool>()
        for (x in 0 .. 2){
            val tool = Tool(x, x.toString(), x)
            list.add(tool)
        }
        listOfTools.value = list
    }
    fun loadInventoryManual(){
        val list = mutableListOf<FlowerInventory>()
        for (x in 1 .. 5){
            val flowerInventory = FlowerInventory(x, 6-x)
            list.add(flowerInventory)
        }
        listOfInventoryFlowers.value = list
    }
}