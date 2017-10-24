package com.deakishin.hotelsapp.model.repository.server

import android.os.SystemClock
import com.deakishin.hotelsapp.model.entities.Hotel
import com.deakishin.hotelsapp.model.entities.HotelDetails

/** Dummy implementation of the server service. */
class ServerServiceDummyImpl : ServerService {

    override fun loadHotels(): List<Hotel> {
        SystemClock.sleep(2500)
        val hotels = (1..20).map {
            Hotel(it.toLong(), "Hotel $it", "Address $it",
                    (it % 5).toFloat(),
                    (it * 100).toFloat(),
                    (1..(20 - it)).toList())
        }
        return hotels
    }

    override fun loadHotelDetails(hotelId: Long): HotelDetails {
        SystemClock.sleep(1500)
        return HotelDetails(
                Hotel(hotelId, "Hotel $hotelId", "Address $hotelId", 1F, 100F, null),
                "https://raw.githubusercontent.com/akgoal/KotlinSample/master/api/hotels/img/1.jpg",
                100F, -200F
        )
    }
}