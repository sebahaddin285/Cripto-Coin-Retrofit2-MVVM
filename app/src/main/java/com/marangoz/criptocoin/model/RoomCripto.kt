package com.marangoz.criptocoin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "table_cripto")
class RoomCripto(
    @ColumnInfo(name = "asset_id") @NotNull var asset_id: String,
    @ColumnInfo(name = "price_usd") @NotNull var price_usd: Double,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int? = null


    )
