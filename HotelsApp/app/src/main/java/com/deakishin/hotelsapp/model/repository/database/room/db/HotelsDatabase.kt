package com.deakishin.hotelsapp.model.repository.database.room.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.deakishin.hotelsapp.model.repository.database.room.dao.HotelsDao
import com.deakishin.hotelsapp.model.repository.database.room.entities.HotelDbE

@Database(entities = arrayOf(HotelDbE::class), version = 1, exportSchema = false)
abstract class HotelsDatabase : RoomDatabase() {

    abstract fun hotelsDao(): HotelsDao
}