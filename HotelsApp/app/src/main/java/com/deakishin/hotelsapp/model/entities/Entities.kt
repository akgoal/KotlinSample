package com.deakishin.hotelsapp.model.entities

/** Model class for a hotel. */
data class Hotel(val id: Long, val name: String, val address: String,
                 val stars: Float, val distance: Float,
                 val availableSuits: List<Int>?) {

    /** Number of available suits. */
    val availableSuitsCount: Int
        get() = availableSuits?.size ?: 0
}

/** Model class for hotel details. */
data class HotelDetails(val basicInfo: Hotel,
                        val imageUrl: String,
                        val lat: Float, val lon: Float)