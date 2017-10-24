package com.deakishin.hotelsapp.model.repository.database

import com.deakishin.hotelsapp.model.entities.Hotel

/** Interface for caching data in a local database. */
interface DatabaseService {

    /** Loads hotels from the database.
     * @return List of loaded hotels. Empty list is considered to be a valid result.
     * If there is no saved data in the database, returns null. */
    fun loadHotels(): List<Hotel>?

    /** Saves hotels to the local database.
     * @param hotels Hotels to save. If null, local cache gets cleared. */
    fun saveHotels(hotels: List<Hotel>?)
}