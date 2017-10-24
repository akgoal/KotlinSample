package com.deakishin.hotelsapp.model.repository.server.rest.dto

import com.deakishin.hotelsapp.model.entities.Hotel
import com.deakishin.hotelsapp.model.entities.HotelDetails
import com.deakishin.hotelsapp.model.repository.server.rest.api.HotelsApi
import com.deakishin.hotelsapp.utils.extensions.logError

/** Class for converting between DTO and model entities. */
class DataMapper {

    /** Converts a DTO to a model entity.  */
    fun convertFromDto(dto: HotelDto) = with(dto) {
        Hotel(id, name, address,
                stars, distance, parseSuits(available_suites))
    }

    /** Parses list of suits from a String. */
    private fun parseSuits(suitsStr: String?): List<Int>? {
        return suitsStr?.let {
            it.split(":")
                    .filter { !it.isBlank() }
                    .map {
                        try {
                            it.toInt()
                        } catch (e: NumberFormatException) {
                            logError(e, "Failed to parse suits from a String")
                            return null
                        }
                    }
        }
    }

    /** Converts a DTO to a model entity.  */
    fun convertFromDto(dto: HotelDetailsDto) = with(dto) {
        HotelDetails(Hotel(id, name, address, stars, distance, parseSuits(available_suits)),
                buildImgUrl(image), lat.toFloat(), lon.toFloat())
    }

    /** Build hotel's image URL by the name of the image. */
    fun buildImgUrl(imageName: String?) =
            imageName?.let { HotelsApi.HOTEL_IMAGE_BASE_URL + it } ?: ""
}