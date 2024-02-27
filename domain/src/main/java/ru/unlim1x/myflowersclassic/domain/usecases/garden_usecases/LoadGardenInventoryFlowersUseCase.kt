package ru.unlim1x.myflowersclassic.domain.usecases.garden_usecases

import io.reactivex.rxjava3.core.Observable
import ru.unlim1x.myflowersclassic.domain.enteties.FlowerShort
import ru.unlim1x.myflowersclassic.domain.interfaces.GardenRepository

class LoadGardenInventoryFlowersUseCase(private val gardenRepository: GardenRepository) {
    fun execute():Observable<List<FlowerShort>>{
        return gardenRepository.loadInventoryFlowers()
    }
}