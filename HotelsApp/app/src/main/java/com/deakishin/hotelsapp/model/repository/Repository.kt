package com.deakishin.hotelsapp.model.repository

import com.deakishin.hotelsapp.model.entities.Hotel
import com.deakishin.hotelsapp.model.entities.HotelDetails

/** Interface for a Repository to load data from. */
interface Repository {

    /** Loads list of hotels.
     * @param forceUpdate True if data must be updated even if it is already loaded.
     * @param dataListener Listener to data loading events. */
    fun loadHotels(forceUpdate: Boolean, dataListener: DataListener<List<Hotel>>?)

    /** Removes listener to hotels loading events.
     * @param dataListener Listener to remove. */
    fun removeHotelsListener(dataListener: DataListener<List<Hotel>>)

    /** Loads hotel details.
     * @param forceUpdate True if data must be updated even if it is already loaded.
     * @param dataListener Listener to data loading events.
     * @param hotelId Id of the hotel.*/
    fun loadHotelDetails(hotelId: Long, forceUpdate: Boolean, dataListener: DataListener<HotelDetails>?)

    /** Removes listener to hotel details loading events.
     * @param dataListener Listener to remove.
     * @param hotelId Id of the hotel*/
    fun removeHotelDetailsListener(hotelId: Long, dataListener: DataListener<HotelDetails>)
}

/** Interface for listening to data loading events.
 * @param T Type of loaded data. */
interface DataListener<in T> {

    /** Invoked when data is currently loading.
     * @param lastData Last loaded data. */
    fun onDataLoading(lastData: T?)

    /** Invoked when data is loaded.
     * @param loadedData Data that was loaded. */
    fun onDataLoaded(loadedData: T?)

    /** Invoked when there was an error while loading data.
     * @param lastData Last successfully loaded data. */
    fun onError(lastData: T?)
}