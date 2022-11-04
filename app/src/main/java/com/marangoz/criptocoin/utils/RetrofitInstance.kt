package com.marangoz.criptocoin.utils

import com.marangoz.criptocoin.api.CriptoDao
import com.marangoz.criptocoin.api.MyIntercepter
import com.marangoz.criptocoin.utils.Constans.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(MyIntercepter())
    }.build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val api:CriptoDao by lazy {
        retrofit.create(CriptoDao::class.java)
    }

}