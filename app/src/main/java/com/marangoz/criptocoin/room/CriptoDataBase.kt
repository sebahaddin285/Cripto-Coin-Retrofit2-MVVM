package com.marangoz.criptocoin.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.marangoz.criptocoin.model.RoomCripto

@Database(entities = [RoomCripto::class], version = 1)
abstract class CriptoDataBase : RoomDatabase() {
    abstract fun getCriptoDao() : CriptoDaoo

    companion object{
        @Volatile
        var INSTANCE : CriptoDataBase? = null

        fun accsessDatabase(context : Context) : CriptoDataBase?{
            if (INSTANCE == null){
                synchronized(CriptoDataBase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        CriptoDataBase::class.java, "cripto.sqlite")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }

}