package com.deakishin.hotelsapp.ui.fragments.hotels

import com.deakishin.hotelsapp.model.entities.Hotel
import com.deakishin.hotelsapp.ui.mvp.MvpView

/**
 * MVP View that displays list of hotels.
 */
interface MvpHotelsView : MvpView {

    /** Sets list of hotels to display. */
    fun setHotels(hotels: List<Hotel>?)

    /**
     * Shows/hides loading indicator.
     */
    fun showLoadingIndicator(toShow: Boolean)

    /**
     * Shows/hides message about empty hotel list.
     */
    fun showNoHotelsMessage(toShow: Boolean)

    /**
     * Shows/hides hotels loading error.
     */
    fun showHotelLoadingError(toShow: Boolean)

    /** Shows hotel details.
     * @param hotelId Id of the hotel. */
    fun showHotelDetails(hotelId: Long)
}