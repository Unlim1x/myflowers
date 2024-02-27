package ru.unlim1x.myflowersclassic.domain.usecases.main_usecases

import ru.unlim1x.myflowersclassic.domain.interfaces.FlowersNetworkRepository
import ru.unlim1x.myflowersclassic.domain.enteties.FlowerShort

class LoadOneFlowerUseCase(private val flowersRepository: FlowersNetworkRepository) {

    suspend fun execute(): FlowerShort? {
        val flower = flowersRepository.loadFromInternet()
        return flower
    }

}