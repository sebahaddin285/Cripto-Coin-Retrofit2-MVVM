package com.marangoz.criptocoin.repository

import com.marangoz.criptocoin.model.Cripto
import com.marangoz.criptocoin.model.RoomCripto
import com.marangoz.criptocoin.room.CriptoDaoo
import com.marangoz.criptocoin.room.CriptoDataBase
import com.marangoz.criptocoin.utils.RetrofitInstance
import retrofit2.Response

class RoomRepo(val mDao : CriptoDaoo) {

    suspend fun getCriptoRoom() : List<RoomCripto>{
        return mDao.allCripto()
    }
    suspend fun insertCriptoRoom(cripto : Cripto) {
        val roomCripto = RoomCripto(cripto.asset_id,cripto.price_usd)
        mDao.insertCripto(roomCripto)
    }
    suspend fun deleteCriptoRoom(cripto: RoomCripto) {
        mDao.deleteCripto(cripto)
    }
    suspend fun updateCriptoRoom(cripto: Cripto) {
        val list = mDao.allCripto()
        list.forEach {
            if (cripto.asset_id == it.asset_id){
                val roomCripto = RoomCripto(cripto.asset_id,cripto.price_usd,it.id)
                mDao.updateCripto(roomCripto)
            }
        }

    }



}