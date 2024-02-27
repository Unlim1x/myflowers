package ru.unlim1x.myflowersclassic.data

import ru.unlim1x.myflowersclassic.domain.enteties.FlowerShort

interface FlowersLoadNext {
    suspend fun loadNext(): FlowerShort?
}