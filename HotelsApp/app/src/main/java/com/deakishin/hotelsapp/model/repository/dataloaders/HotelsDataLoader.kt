package com.deakishin.hotelsapp.model.repository.dataloaders

import com.deakishin.hotelsapp.model.entities.Hotel
import com.deakishin.hotelsapp.model.repository.database.DatabaseService
import com.deakishin.hotelsapp.model.repository.server.ServerService

/** Loader to load hotels data.
 * @param server Service to load data from the server.
 * @param database Service to cache data in the local database.*/
class HotelsDataLoader(private val server: ServerService, private val database: DatabaseService) :
        DataLoader<List<Hotel>>() {

    /** Loaded hotels that are sorted by the number of available suits. */
    override var data: List<Hotel>? = null
        get() = field?.sortedByDescending { it.availableSuitsCount }

    override fun loadServerData(): List<Hotel>? {
        return server.loadHotels()
    }

    override fun loadDbData(): List<Hotel>? {
        return database.loadHotels()
    }

    override fun saveDataToDb(data: List<Hotel>?) {
        database.saveHotels(data)
    }

}