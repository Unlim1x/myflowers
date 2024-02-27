package ru.unlim1x.myflowersclassic.domain.usecases.main_usecases

import ru.unlim1x.myflowersclassic.domain.interfaces.FlowersNetworkRepository
import ru.unlim1x.myflowersclassic.domain.enteties.FlowerShort

class LoadSomeFlowersListUseCase(private val flowersRepository: FlowersNetworkRepository) {
    suspend fun execute(): List<FlowerShort?> {
        val mutableListOfFlowerShort = mutableListOf<FlowerShort?>()
        for (x in 1..5) {
            val flower = flowersRepository.loadFromInternet()
            if(flower!=null)
                mutableListOfFlowerShort.add(flower)
        }
        return mutableListOfFlowerShort
    }
}