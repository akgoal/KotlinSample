package com.deakishin.hotelsapp.model.repository.database.room

import android.arch.persistence.room.Room
import android.content.Context
import com.deakishin.hotelsapp.model.entities.Hotel
import com.deakishin.hotelsapp.model.repository.database.DatabaseService
import com.deakishin.hotelsapp.model.repository.database.room.db.HotelsDatabase
import com.deakishin.hotelsapp.model.repository.database.room.entities.DbMapper
import com.deakishin.hotelsapp.model.repository.database.room.entities.HotelDbE

/** Database service implementation that uses Room Persistence Library. */
class DatabaseServiceRoomImpl(context: Context, private val mapper: DbMapper = DbMapper()) : DatabaseService {

    private val hotelsDb = Room.databaseBuilder(
            context, HotelsDatabase::class.java, "localDb")
            .fallbackToDestructiveMigration()
            .build()

    private val hotelsDao = hotelsDb.hotelsDao()

    override fun loadHotels(): List<Hotel>? {
        val hotels = hotelsDao.getHotels()?.map {
            mapper.convertDbEntity(it)
        }
        return hotels?.let { if (it.isEmpty()) null else hotels }
    }

    override fun saveHotels(hotels: List<Hotel>?) {
        hotelsDao.deleteHotels()
        var order = 1;
        hotelsDao.saveHotels(hotels?.map {
            mapper.parseDbEntity(it, order++)
        } ?: listOf<HotelDbE>())
    }

}