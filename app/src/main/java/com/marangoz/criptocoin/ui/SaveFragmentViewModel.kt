package com.marangoz.criptocoin.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marangoz.criptocoin.model.Cripto
import com.marangoz.criptocoin.model.RoomCripto
import com.marangoz.criptocoin.repository.RoomRepo
import com.marangoz.criptocoin.room.CriptoDaoo
import com.marangoz.criptocoin.utils.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Response

class SaveFragmentViewModel(private val mDao: CriptoDaoo) : ViewModel() {
    val roomRepo = RoomRepo(mDao)
    val roomList: MutableLiveData<List<RoomCripto>> = MutableLiveData()
    val criptoList : ArrayList<List<Cripto>> = ArrayList()
    val idList : ArrayList<Int> = ArrayList()


    fun getData() {
        viewModelScope.launch {
            val list = roomRepo.getCriptoRoom()
            list.forEach {
                RetrofitInstance.api.getCustomCriptoData(it.asset_id).body()
                    ?.let { it1 -> criptoList.add(it1) }
                    it.id?.let { it1 -> idList.add(it1) }
            }
            criptoList.forEach(){
                it.forEach(){
                    roomRepo.updateCriptoRoom(it)
                }
            }


            roomList.value = roomRepo.getCriptoRoom()

        }
    }
    fun deleteData(cripto: RoomCripto){
        viewModelScope.launch {
            roomRepo.deleteCriptoRoom(cripto)
            roomList.value = roomRepo.getCriptoRoom()
        }
    }


}