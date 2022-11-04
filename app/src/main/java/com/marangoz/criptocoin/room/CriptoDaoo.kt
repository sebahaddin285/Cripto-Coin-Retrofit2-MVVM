package com.marangoz.criptocoin.room

import androidx.room.*
import com.marangoz.criptocoin.model.RoomCripto

@Dao
interface CriptoDaoo {
    @Query("select * from table_cripto")
    suspend fun allCripto(): List<RoomCripto>

    @Delete
    suspend fun deleteCripto(RoomCripto: RoomCripto)

    @Insert
    suspend fun insertCripto(RoomCripto: RoomCripto)

    @Update
    suspend fun updateCripto(RoomCripto: RoomCripto)

}