package ru.unlim1x.myflowersclassic.di

import org.koin.dsl.module
import ru.unlim1x.myflowersclassic.domain.usecases.main_usecases.GetImageAddressUseCase
import ru.unlim1x.myflowersclassic.domain.usecases.main_usecases.LoadOneFlowerUseCase
import ru.unlim1x.myflowersclassic.domain.usecases.main_usecases.LoadSomeFlowersListUseCase


val domainModule = module {
    factory<LoadSomeFlowersListUseCase> {
        LoadSomeFlowersListUseCase(flowersRepository = get())
    }

    factory<LoadOneFlowerUseCase> {
        LoadOneFlowerUseCase(flowersRepository = get())
    }

    factory<GetImageAddressUseCase> {
        GetImageAddressUseCase(flowersRepository = get())
    }
}