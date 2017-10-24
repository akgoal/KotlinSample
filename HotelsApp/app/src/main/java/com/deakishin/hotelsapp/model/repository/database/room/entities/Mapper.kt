package com.deakishin.hotelsapp.model.repository.database.room.entities

import com.deakishin.hotelsapp.model.entities.Hotel
import com.deakishin.hotelsapp.utils.extensions.logError

/** Converter between domain entities and database entities. */
class DbMapper {

    /** Converts a database entity to a domain object. */
    fun convertDbEntity(dbEntity: HotelDbE): Hotel {
        return Hotel(dbEntity.id, dbEntity.name, dbEntity.address,
                dbEntity.stars, dbEntity.distance, parseSuits(dbEntity.suits))
    }

    /** Converts a domain object to a database entity. */
    fun parseDbEntity(hotel: Hotel, order: Int): HotelDbE {
        return HotelDbE(hotel.id, hotel.name, hotel.address,
                hotel.stars, hotel.distance, convertSuits(hotel.availableSuits), order)
    }

    private val SUITS_DEL = ";"

    /** Parses a list of suits from a String. */
    private fun parseSuits(suitsStr: String?): List<Int>? {
        return suitsStr?.let {
            if (it.isBlank()) return null

            it.split(SUITS_DEL)
                    .filter { !it.isBlank() }
                    .map {
                        try {
                            it.toInt()
                        } catch (e: NumberFormatException) {
                            logError(e, "Error parsing suits in a database entity")
                            return null
                        }
                    }
        }
    }

    /** Converts a list of suits to a String. */
    private fun convertSuits(suits: List<Int>?): String? {
        return suits?.let {
            it.fold("") { res, suit -> res + suit.toString() + SUITS_DEL }
        }
    }
}