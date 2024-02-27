package ru.unlim1x.myflowersclassic.data.implementations

import ru.unlim1x.myflowersclassic.data.FlowersLoadNext
import ru.unlim1x.myflowersclassic.data.FlowersNetworkLoading
import ru.unlim1x.myflowersclassic.data.enteties.FlowerData
import ru.unlim1x.myflowersclassic.domain.enteties.FlowerShort

class FlowersLoadNextImpl(private val flowersNetworkLoading: FlowersNetworkLoading) :
    FlowersLoadNext {
    private var list: List<FlowerData> = listOf()
    private lateinit var iterator: Iterator<FlowerData>
    override suspend fun loadNext(): FlowerShort? {
        if (list.isEmpty()) {
            list = flowersNetworkLoading.load()
            iterator = list.iterator()
        }

        if (iterator.hasNext()) {
            val dataflower = iterator.next()
            val domainflower: FlowerShort = FlowerShort(
                name = dataflower.name,
                watering = dataflower.watering,
                period = dataflower.period,
                id = dataflower.id
            )
            return domainflower
        }
        return null
    }
}