package com.deakishin.hotelsapp.model.repository.server

import com.deakishin.hotelsapp.model.entities.Hotel
import com.deakishin.hotelsapp.model.entities.HotelDetails

/** Interface for loading data from the server. */
interface ServerService {

    /** Loads hotels data from the server.
     * @return List of loaded hotels.
     * @throws Exception If loading failed. */
    fun loadHotels(): List<Hotel>

    /** Loads hotel details.
     * @param hotelId Id of the hotel.
     * @throws Exception If loading failed.*/
    fun loadHotelDetails(hotelId: Long): HotelDetails?
}