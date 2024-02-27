package ru.unlim1x.myflowersclassic.domain.interfaces

import io.reactivex.rxjava3.core.Observable
import ru.unlim1x.myflowersclassic.domain.enteties.FlowerShort

interface FlowersNetworkRepository {
    suspend fun loadFromInternet(): FlowerShort?
    fun getImageAddress(imageId:Int):Observable<String>
    suspend fun loadFlowerById(id: Int): FlowerShort?
}