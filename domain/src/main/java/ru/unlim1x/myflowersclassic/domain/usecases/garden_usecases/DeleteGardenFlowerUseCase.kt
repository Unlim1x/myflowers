package ru.unlim1x.myflowersclassic.domain.usecases.garden_usecases

import ru.unlim1x.myflowersclassic.domain.enteties.FlowerGarden
import ru.unlim1x.myflowersclassic.domain.enteties.FlowerShort
import ru.unlim1x.myflowersclassic.domain.interfaces.GardenRepository

class DeleteGardenFlowerUseCase(private val gardenRepository: GardenRepository) {
    fun execute(flower : FlowerGarden):Boolean{
        return gardenRepository.deleteFlower(flower)
    }
}