package ru.unlim1x.myflowersclassic.data

import ru.unlim1x.myflowersclassic.data.enteties.FlowerData

interface FlowersNetworkLoading {
    suspend fun load(): List<FlowerData>
}