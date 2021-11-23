package com.playground.spotipret.common

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServicesBuilder {
    val networkFlipperPlugin = NetworkFlipperPlugin()

    private val client = OkHttpClient.Builder()
        .addNetworkInterceptor(FlipperOkhttpInterceptor(networkFlipperPlugin))
        .build()


    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun<T> builderService(Service:Class<T>):T{
        return retrofit.create(Service)
    }
}