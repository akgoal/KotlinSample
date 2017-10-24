package com.deakishin.hotelsapp.model.repository.database.room.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/** Database entity for a hotel. */
@Entity(tableName = "hotels")
data class HotelDbE(
        @ColumnInfo(name = "id") @PrimaryKey val id: Long,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "address") val address: String,
        @ColumnInfo(name = "stars") val stars: Float,
        @ColumnInfo(name = "distance") val distance: Float,
        @ColumnInfo(name = "suits") val suits: String?,
        @ColumnInfo(name="def_order") val order: Int
)