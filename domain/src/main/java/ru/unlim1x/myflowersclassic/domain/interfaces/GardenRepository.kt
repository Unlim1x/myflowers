package ru.unlim1x.myflowersclassic.domain.interfaces

import io.reactivex.rxjava3.core.Observable
import ru.unlim1x.myflowersclassic.domain.enteties.FlowerGarden
import ru.unlim1x.myflowersclassic.domain.enteties.FlowerShort
import ru.unlim1x.myflowersclassic.domain.enteties.Tool

interface GardenRepository {
    fun loadPlantedFlowers() : Observable<List<FlowerGarden>>
    fun loadInventoryFlowers() : Observable<List<FlowerShort>>
    fun loadTools():Observable<List<Tool>>
    fun plantFlower(flower : FlowerShort) : Boolean
    fun deleteFlower(flower : FlowerGarden) : Boolean
    fun waterFlower(flower : FlowerGarden) : Boolean
    fun feedFlower(flower : FlowerGarden) : Boolean
}