package com.marangoz.criptocoin.api

import com.marangoz.criptocoin.model.Cripto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CriptoDao {

    @GET("v1/assets")
    suspend fun getCriptoData() : Response<List<Cripto>>

    @GET("v1/assets/{asset_id}")
    suspend fun getCustomCriptoData(
        @Path("asset_id") asset_id:String
    ) : Response<List<Cripto>>

}