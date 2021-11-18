package com.playground.spotipret.common

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServicesBuilder {
    private val client = OkHttpClient.Builder().build()

    private val retrofit =Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun<T> builderService(Service:Class<T>):T{
        return retrofit.create(Service)
    }
}