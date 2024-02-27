package ru.unlim1x.myflowersclassic.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.unlim1x.myflowersclassic.data.Strings

object RetrofitClient {
    private var retrofit: Retrofit? = null
    private val baseUrl = Strings.BASE_URL

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun get(): RetrofitQueue {
        return retrofit!!.create(RetrofitQueue::class.java)
    }
}