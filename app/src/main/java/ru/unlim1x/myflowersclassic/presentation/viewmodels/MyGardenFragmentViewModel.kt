package ru.unlim1x.myflowersclassic.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.unlim1x.myflowersclassic.data.enteties.FlowerInventory
import ru.unlim1x.myflowersclassic.domain.enteties.FlowerGarden
import ru.unlim1x.myflowersclassic.domain.enteties.FlowerShort
import ru.unlim1x.myflowersclassic.domain.enteties.Tool
import ru.unlim1x.myflowersclassic.domain.interfaces.GardenUseCase
import ru.unlim1x.myflowersclassic.domain.usecases.garden_usecases.DeleteGardenFlowerUseCase
import ru.unlim1x.myflowersclassic.domain.usecases.garden_usecases.LoadGardenFlowersUseCase
import ru.unlim1x.myflowersclassic.domain.usecases.garden_usecases.LoadGardenInventoryFlowersUseCase
import ru.unlim1x.myflowersclassic.domain.usecases.garden_usecases.LoadGardenToolsUseCase
import ru.unlim1x.myflowersclassic.domain.usecases.garden_usecases.PlantGardenFlowerUseCase

class MyGardenFragmentViewModel(
//    private val loadGardenFlowersUseCase : LoadGardenFlowersUseCase,
//    private val loadGardenInventoryFlowersUseCase: LoadGardenInventoryFlowersUseCase,
//    private val loadGardenToolsUseCase: LoadGardenToolsUseCase,
//    private val plantGardenFlowerUseCase: PlantGardenFlowerUseCase,
//    private val deleteGardenFlowerUseCase: DeleteGardenFlowerUseCase
) : ViewModel() {

    private val disposeBag: CompositeDisposable by lazy { CompositeDisposable() }
    val listOfTools :  MutableLiveData<MutableList<Tool>> = MutableLiveData()
    val listOfPlantedFlowers : MutableLiveData<MutableList<FlowerGarden>> = MutableLiveData()
    val listOfInventoryFlowers : MutableLiveData<MutableList<FlowerInventory>> = MutableLiveData()


/*
    fun loadPlantedFlowers(){
        val disposable = loadGardenFlowersUseCase.execute()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
               listOfPlantedFlowers.postValue(it.toMutableList())
            },
                {
                    Log.e("GVM", "error occured: $it")
                },
                {
                    Log.e("GVM", "completed loading")
                })
        disposeBag.add(disposable)
    }

    fun loadInventoryFlowers(){
        val disposable = loadGardenInventoryFlowersUseCase.execute()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //listOfInventoryFlowers.postValue(it.toMutableList())
            },
                {
                    Log.e("GVM", "error occured: $it")
                },
                {
                    Log.e("GVM", "completed loading")
                })
        disposeBag.add(disposable)
    }

    fun loadTools(){
        val disposable = loadGardenToolsUseCase.execute()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listOfTools.postValue(it.toMutableList())
            },
                {
                    Log.e("GVM", "error occured: $it")
                },
                {
                    Log.e("GVM", "completed loading")
                })
        disposeBag.add(disposable)
    }

    fun plantGarden(flowerShort: FlowerShort){
        plantGardenFlowerUseCase.execute(flowerShort)
    }

    fun deleteFlower(flowerGarden: FlowerGarden){
        deleteGardenFlowerUseCase.execute(flowerGarden)
    }

    */

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