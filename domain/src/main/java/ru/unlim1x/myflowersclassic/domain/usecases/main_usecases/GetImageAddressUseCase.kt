package ru.unlim1x.myflowersclassic.domain.usecases.main_usecases

import io.reactivex.rxjava3.core.Observable
import ru.unlim1x.myflowersclassic.domain.interfaces.FlowersNetworkRepository

class GetImageAddressUseCase(private val flowersRepository: FlowersNetworkRepository) {
    fun execute(imageId:Int): Observable<String> {
        return flowersRepository.getImageAddress(imageId = imageId)
    }
}