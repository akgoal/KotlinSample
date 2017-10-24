package com.deakishin.hotelsapp.model.repository.database.room.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.deakishin.hotelsapp.model.repository.database.room.entities.HotelDbE

@Dao
interface HotelsDao {

    @Query("SELECT * FROM hotels ORDER BY def_order ASC")
    fun getHotels(): List<HotelDbE>?

    @Query("DELETE FROM hotels")
    fun deleteHotels()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveHotels(hotels: List<HotelDbE>)
}