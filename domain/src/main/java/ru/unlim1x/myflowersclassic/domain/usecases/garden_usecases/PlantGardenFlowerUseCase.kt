package ru.unlim1x.myflowersclassic.domain.usecases.garden_usecases

import ru.unlim1x.myflowersclassic.domain.enteties.FlowerShort
import ru.unlim1x.myflowersclassic.domain.interfaces.GardenRepository

class PlantGardenFlowerUseCase(private val gardenRepository: GardenRepository) {
    fun execute(flower : FlowerShort):Boolean{
        return gardenRepository.plantFlower(flower)
    }
}