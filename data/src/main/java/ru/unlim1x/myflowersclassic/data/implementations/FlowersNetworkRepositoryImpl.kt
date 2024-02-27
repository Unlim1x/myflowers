package ru.unlim1x.myflowersclassic.data.implementations


import io.reactivex.rxjava3.core.Observable
import ru.unlim1x.myflowersclassic.data.FlowersLoadNext

import ru.unlim1x.myflowersclassic.data.Strings
import ru.unlim1x.myflowersclassic.domain.interfaces.FlowersNetworkRepository
import ru.unlim1x.myflowersclassic.domain.enteties.FlowerShort

class FlowersNetworkRepositoryImpl(private val flowersLoadNext: FlowersLoadNext) : FlowersNetworkRepository {

    override suspend fun loadFromInternet(): FlowerShort? {
        return flowersLoadNext.loadNext()
    }

    override fun getImageAddress(imageId:Int): Observable<String> {
        return Observable.just(Strings.PICS_FOLDER+imageId.toString()+Strings.PICS_FORMAT_JPG)
    }

    override suspend fun loadFlowerById(id: Int): FlowerShort? {
        return null
    }
}