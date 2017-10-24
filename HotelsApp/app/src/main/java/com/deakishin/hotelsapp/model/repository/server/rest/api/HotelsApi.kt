package com.deakishin.hotelsapp.model.repository.server.rest.api

import com.deakishin.hotelsapp.model.repository.server.rest.dto.HotelDetailsDto
import com.deakishin.hotelsapp.model.repository.server.rest.dto.HotelDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/** Rest Api to set Retrofit to load hotels data from the server. */
interface HotelsApi {

    companion object {
        /** Base URL address for hotels api. */
        val HOTELS_URL =
                "https://raw.githubusercontent.com/akgoal/KotlinSample/master/api/hotels/"

        /**
         * Base URL address for hotel images.
         */
        val HOTEL_IMAGE_BASE_URL = HOTELS_URL + "img/"
    }

    @GET("all.json")
    fun hotels(): Call<List<HotelDto>>

    @GET("{hotel_id}.json")
    fun hotelDetails(@Path("hotel_id") hotelId: Long): Call<HotelDetailsDto>
}