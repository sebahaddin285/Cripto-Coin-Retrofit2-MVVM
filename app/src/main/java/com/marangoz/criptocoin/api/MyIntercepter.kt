package com.marangoz.criptocoin.api

import com.marangoz.criptocoin.utils.Constans.Companion.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class MyIntercepter : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request() // headers içinde istek atacaksak bunu yapmamız lazım
            .newBuilder()
            .addHeader("X-CoinAPI-Key",API_KEY)
            .build()
        return chain.proceed(request)
    }
}