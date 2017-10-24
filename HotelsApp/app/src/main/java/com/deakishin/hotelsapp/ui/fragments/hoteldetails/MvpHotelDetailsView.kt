package com.deakishin.hotelsapp.ui.fragments.hoteldetails

import com.deakishin.hotelsapp.model.entities.HotelDetails
import com.deakishin.hotelsapp.ui.mvp.MvpView

/** MVP View that displays hotel details. */
interface MvpHotelDetailsView : MvpView {

    /** Sets hotel details to display. */
    fun setHotelDetails(hotelDetails:HotelDetails?)

    /** Shows/hides loading indicator. */
    fun showLoadingIndicator(toShow:Boolean)

    /** Shows/hides loading error. */
    fun showLoadingError(toShow:Boolean)
}