package com.deakishin.hotelsapp.model.repository.server.rest

import com.deakishin.hotelsapp.model.entities.Hotel
import com.deakishin.hotelsapp.model.entities.HotelDetails
import com.deakishin.hotelsapp.model.repository.server.ServerService
import com.deakishin.hotelsapp.model.repository.server.rest.api.HotelsApi
import com.deakishin.hotelsapp.model.repository.server.rest.dto.DataMapper
import com.deakishin.hotelsapp.model.repository.server.rest.utils.ErrorUtils
import com.deakishin.hotelsapp.utils.extensions.logError
import com.deakishin.hotelsapp.utils.extensions.logInfo
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/** Implementation of the Server service that uses Retrofit to load data from the server. */
class ServerServiceRetrofitImpl(private val mapper: DataMapper = DataMapper())
    : ServerService {

    private val retrofit = Retrofit.Builder()
            .baseUrl(HotelsApi.HOTELS_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    private val hotelsApi =
            retrofit.create(HotelsApi::class.java)

    private val errorUtils = ErrorUtils(retrofit)

    override fun loadHotels(): List<Hotel> {
        return executeCall(hotelsApi.hotels())?.map {
            mapper.convertFromDto(it)
        } ?: listOf()
    }

    override fun loadHotelDetails(hotelId: Long): HotelDetails? {
        return executeCall(hotelsApi.hotelDetails(hotelId))?.let {
            mapper.convertFromDto(it)
        }
    }

    /** Executes call to the server.
     * @param T Type of loaded data.
     * @throws Exception If the execution fails. */
    private fun <T> executeCall(call: Call<T>): T? {
        try {
            val response = call.execute()
            if (response.isSuccessful) {
                return response.body()
            } else throw Exception("Bad response from server: "
                    + errorUtils.parseError(response))
        } catch (e: Exception) {
            logError(e, "Failed to load data from the network")
            throw e
        }

    }

}