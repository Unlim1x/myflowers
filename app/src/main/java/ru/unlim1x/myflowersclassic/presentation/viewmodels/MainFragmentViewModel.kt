package ru.unlim1x.myflowersclassic.presentation.viewmodels


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.unlim1x.myflowersclassic.domain.enteties.FlowerShort
import ru.unlim1x.myflowersclassic.domain.usecases.main_usecases.GetImageAddressUseCase
import ru.unlim1x.myflowersclassic.domain.usecases.main_usecases.LoadSomeFlowersListUseCase

class MainFragmentViewModel(
    private val loadSomeFlowersListUseCase: LoadSomeFlowersListUseCase,
    private val getImageAddressUseCase: GetImageAddressUseCase,

    ) : ViewModel() {
    val listOfFlowersLD: MutableLiveData<MutableList<FlowerShort?>> = MutableLiveData()
    val imageAddress: MutableLiveData<HashMap<Int, HashMap<Int, String>>> = MutableLiveData()
    private val listOfFlowers: MutableList<FlowerShort?> = mutableListOf()
    private val disposeBag: CompositeDisposable by lazy { CompositeDisposable() }
    private val listOfObservedImages = mutableListOf<Int>()


    fun loadSomeFlowers() {
        viewModelScope.launch(Dispatchers.IO) {
            listOfFlowers.addAll(loadSomeFlowersListUseCase.execute())
            listOfFlowersLD.postValue(listOfFlowers)
        }

    }


    fun loadFlowerImage(id: Int, parameter: Int) {

        listOfObservedImages.add(id)
        val disposable = getImageAddressUseCase.execute(parameter)
            .map { string -> hashMapOf(parameter to string) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe({

                imageAddress.value = hashMapOf(id to it)
                Log.e("VM", "Image address loaded: $it")
            },
                {
                    Log.e("VM", "error occured: $it")
                },
                {
                    Log.e("VM", "completed loading")
                }
            )
        disposeBag.add(disposable)
    }


}