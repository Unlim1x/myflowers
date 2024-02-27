package ru.unlim1x.myflowersclassic.data.implementations

import ru.unlim1x.myflowersclassic.data.FlowersNetworkLoading
import ru.unlim1x.myflowersclassic.data.Strings
import ru.unlim1x.myflowersclassic.data.enteties.FlowerData
import ru.unlim1x.myflowersclassic.data.retrofit.RetrofitClient

class FlowersNetworkLoadingImpl : FlowersNetworkLoading {
    override suspend fun load(): List<FlowerData> {
        return RetrofitClient.get().loadFlowersList(Strings.FLOWER_FILE_NAME).execute().body()!!
    }
}