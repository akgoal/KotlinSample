package com.deakishin.hotelsapp.model.repository

import com.deakishin.hotelsapp.model.entities.Hotel
import com.deakishin.hotelsapp.model.entities.HotelDetails
import com.deakishin.hotelsapp.model.repository.database.DatabaseService
import com.deakishin.hotelsapp.model.repository.dataloaders.HotelDetailsDataLoader
import com.deakishin.hotelsapp.model.repository.dataloaders.HotelsDataLoader
import com.deakishin.hotelsapp.model.repository.server.ServerService

/** Repository implementation that loads data from the network and saves it in a local database.
 * @param server Server service to load data from the server.
 * @param database Database service to cache loaded data in the local database. */
class RepositoryImpl(private val server: ServerService,
                     private val database: DatabaseService) :
        Repository {

    private val hotelsDataLoader = HotelsDataLoader(server, database)

    private val hotelDetailsDataLoaders = mutableMapOf<Long, HotelDetailsDataLoader>()

    override fun loadHotels(forceUpdate: Boolean, dataListener: DataListener<List<Hotel>>?) {
        hotelsDataLoader.loadData(forceUpdate, dataListener)
    }

    override fun removeHotelsListener(dataListener: DataListener<List<Hotel>>) {
        hotelsDataLoader.removeListener(dataListener)
    }

    override fun loadHotelDetails(hotelId: Long, forceUpdate: Boolean, dataListener: DataListener<HotelDetails>?) {
        getHotelDetailsDataLoader(hotelId).loadData(forceUpdate, dataListener)
    }

    override fun removeHotelDetailsListener(hotelId: Long, dataListener: DataListener<HotelDetails>) {
        getHotelDetailsDataLoader(hotelId).removeListener(dataListener)
    }

    /** Returns hotel details data loader for the hotel with the specified id.
     * If no such loader exists, creates and returns one. */
    private fun getHotelDetailsDataLoader(hotelId: Long) =
            hotelDetailsDataLoaders.getOrPut(hotelId, { HotelDetailsDataLoader(hotelId, server, database) })
}