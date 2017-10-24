package com.deakishin.hotelsapp.ui.fragments.hotels

/** Interface for an activity that can navigate to hotel details.
 *
 * If the host activity wants to be able to organize navigation from the hotels list fragment to hotel details,
 * it must implement this interface. */
interface HotelDetailsNavigationActivity {

    /** Navigates to hotel details.
     * @param hotelId Id of the hotel. */
    fun navigateToHotelDetails(hotelId: Long)
}