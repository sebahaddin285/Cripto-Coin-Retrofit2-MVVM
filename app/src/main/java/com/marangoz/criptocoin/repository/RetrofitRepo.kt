package com.marangoz.criptocoin.repository

import com.marangoz.criptocoin.model.Cripto
import com.marangoz.criptocoin.utils.RetrofitInstance
import retrofit2.Response

class RetrofitRepo {

    suspend fun getCriptoData() : Response<List<Cripto>>{
        return RetrofitInstance.api.getCriptoData()
    }
    suspend fun getCriptoRetrofit(asset_id : String) : Response<List<Cripto>>{
        return RetrofitInstance.api.getCustomCriptoData(asset_id)
    }


}