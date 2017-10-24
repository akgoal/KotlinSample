package com.deakishin.hotelsapp.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.deakishin.hotelsapp.ui.components.SingleFragmentActivity
import com.deakishin.hotelsapp.ui.fragments.hoteldetails.HotelDetailsFragment

/** Activity that displays hotel details. */
class DetailsActivity : SingleFragmentActivity() {

    companion object {
        val EXTRA_HOTEL_ID = "DetailsActivity.hotelId"

        /** Constructs new intent that should be used to start this activity.
         * @param hotelId Id of the hotel. */
        fun newIntent(context: Context, hotelId: Long): Intent =
                Intent(context, DetailsActivity::class.java).putExtra(EXTRA_HOTEL_ID, hotelId)
    }

    private var hotelId: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        hotelId = intent?.getLongExtra(EXTRA_HOTEL_ID, -1) ?: -1
        super.onCreate(savedInstanceState)
    }

    override fun createFragment(): Fragment {
        return HotelDetailsFragment.newInstance(hotelId)
    }
}