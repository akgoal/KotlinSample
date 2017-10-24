package com.deakishin.hotelsapp.model.repository.dataloaders

import com.deakishin.hotelsapp.model.entities.HotelDetails
import com.deakishin.hotelsapp.model.repository.database.DatabaseService
import com.deakishin.hotelsapp.model.repository.server.ServerService

/** Loader to load hotel details.
 * @param server Service to load data from the server.
 * @param database Service to cache data in the local database. */
class HotelDetailsDataLoader(val hotelId: Long,
                             private val server:ServerService,
                             private val database:DatabaseService) : DataLoader<HotelDetails>() {

    override fun loadServerData(): HotelDetails? {
        return server.loadHotelDetails(hotelId)
    }

    override fun loadDbData(): HotelDetails? = null

    override fun saveDataToDb(data: HotelDetails?) {
        // Don't cache hotel details in the database,
        // as we want to always load fresh data from the server.
    }

}