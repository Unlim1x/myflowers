package ru.unlim1x.myflowersclassic.domain.usecases.garden_usecases

import io.reactivex.rxjava3.core.Observable
import ru.unlim1x.myflowersclassic.domain.enteties.Tool
import ru.unlim1x.myflowersclassic.domain.interfaces.GardenRepository
import ru.unlim1x.myflowersclassic.domain.interfaces.GardenUseCase

class LoadGardenToolsUseCase(private val gardenRepository: GardenRepository) {
     fun execute(): Observable<List<Tool>> {
        return gardenRepository.loadTools()
    }
}