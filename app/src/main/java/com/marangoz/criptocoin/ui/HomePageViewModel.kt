package com.marangoz.criptocoin.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marangoz.criptocoin.model.Cripto
import com.marangoz.criptocoin.repository.RetrofitRepo
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response

class HomePageViewModel(val repository : RetrofitRepo) : ViewModel() {
    val criptoList : MutableLiveData<Response<List<Cripto>>> = MutableLiveData()

    fun getCripto(){
        viewModelScope.launch {
            val response = repository.getCriptoData()
            criptoList.value = response
        }
    }
    fun getCriptoRetrofit(asset_id : String){
        viewModelScope.launch {
            val response = repository.getCriptoRetrofit(asset_id)
            criptoList.value = response
        }
    }





}