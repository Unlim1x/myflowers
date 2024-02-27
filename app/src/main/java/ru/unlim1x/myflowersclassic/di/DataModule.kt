package ru.unlim1x.myflowersclassic.di

import org.koin.dsl.module
import ru.unlim1x.myflowersclassic.data.FlowersLoadNext
import ru.unlim1x.myflowersclassic.data.FlowersNetworkLoading
import ru.unlim1x.myflowersclassic.data.implementations.FlowersLoadNextImpl
import ru.unlim1x.myflowersclassic.data.implementations.FlowersNetworkLoadingImpl
import ru.unlim1x.myflowersclassic.data.implementations.FlowersNetworkRepositoryImpl
import ru.unlim1x.myflowersclassic.domain.interfaces.FlowersNetworkRepository



val dataModule = module {
    single<FlowersLoadNext> {
        FlowersLoadNextImpl(flowersNetworkLoading = get())
    }

    single<FlowersNetworkLoading> {
        FlowersNetworkLoadingImpl()
    }

    single<FlowersNetworkRepository> {
        FlowersNetworkRepositoryImpl(flowersLoadNext = get())
    }


}