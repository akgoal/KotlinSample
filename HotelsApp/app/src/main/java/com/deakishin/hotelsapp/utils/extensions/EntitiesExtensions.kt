package com.deakishin.hotelsapp.utils.extensions

import com.deakishin.hotelsapp.model.entities.HotelDetails

val HotelDetails.latLiteral
    get() = if (lat >= 0) "N" else "S"

val HotelDetails.lonLiteral
    get() = if (lon >= 0) "E" else "W"