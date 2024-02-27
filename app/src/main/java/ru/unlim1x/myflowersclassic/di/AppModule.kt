package ru.unlim1x.myflowersclassic.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.unlim1x.myflowersclassic.presentation.viewmodels.InventoryViewModel
import ru.unlim1x.myflowersclassic.presentation.viewmodels.MainFragmentViewModel
import ru.unlim1x.myflowersclassic.presentation.viewmodels.MyGardenFragmentViewModel

val appModule = module {

    viewModel<MainFragmentViewModel> {
        MainFragmentViewModel(
            loadSomeFlowersListUseCase = get(),
            getImageAddressUseCase = get(),
        )
    }

    viewModel<MyGardenFragmentViewModel>{
        MyGardenFragmentViewModel()
    }

    viewModel<InventoryViewModel>(){
        InventoryViewModel()
    }
}