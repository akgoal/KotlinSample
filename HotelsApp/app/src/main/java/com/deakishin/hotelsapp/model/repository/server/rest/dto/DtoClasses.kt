package com.deakishin.hotelsapp.model.repository.server.rest.dto

/* DTO classes that represent data, loaded from the server.
 * Property names must be the same as fields, specified in a JSON response. */

/** Class that represents hotel info loaded from the server. */
data class HotelDto(val id: Long, val name: String, val address: String,
                    val stars: Float, val distance: Float, val available_suites: String)

/** Class that represents hotel details info loaded from the server. */
data class HotelDetailsDto(val id: Long, val name: String, val address: String,
                           val stars: Float, val distance: Float, val available_suits: String,
                           val image: String?, val lat: Double, val lon: Double)