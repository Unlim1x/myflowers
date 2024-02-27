package ru.unlim1x.myflowersclassic.domain.interfaces

interface GardenUseCase<T> {
    fun execute() : T
}