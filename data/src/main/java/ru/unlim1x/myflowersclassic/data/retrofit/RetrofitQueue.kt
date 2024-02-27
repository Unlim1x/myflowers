package ru.unlim1x.myflowersclassic.data.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import ru.unlim1x.myflowersclassic.data.enteties.FlowerData

interface RetrofitQueue {
    @GET("{filename}")
    fun loadFlowersList(@Path("filename") filename: String): Call<MutableList<FlowerData>>
}