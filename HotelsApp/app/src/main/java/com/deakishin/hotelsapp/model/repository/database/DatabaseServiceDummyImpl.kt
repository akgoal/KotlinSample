package com.deakishin.hotelsapp.model.repository.database

import com.deakishin.hotelsapp.model.entities.Hotel

/** Dummy implementation of the database service. */
class DatabaseServiceDummyImpl : DatabaseService {

    override fun loadHotels(): List<Hotel>? {
        return null
    }

    override fun saveHotels(hotels: List<Hotel>?) {
    }

}