package ru.unlim1x.myflowersclassic.domain.enteties

import java.util.Date

data class FlowerGarden(
    val id : Int,
    val imageId: Int,
    val name: Int,
    val phase : PhaseOfFlower,
    val last_watering : Date,
    val number : Int
)
