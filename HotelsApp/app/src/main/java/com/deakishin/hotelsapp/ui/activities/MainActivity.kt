package com.deakishin.hotelsapp.ui.activities

import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.deakishin.hotelsapp.R
import com.deakishin.hotelsapp.ui.components.BaseActivity
import com.deakishin.hotelsapp.ui.fragments.hotels.HotelDetailsNavigationActivity
import com.deakishin.hotelsapp.ui.fragments.hotels.HotelsFragment
import kotlinx.android.synthetic.main.toolbar.*

/** Main activity of the app. */
class MainActivity : BaseActivity(), HotelDetailsNavigationActivity {

    override val toolbar: Toolbar by lazy { customToolbar }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val fm = supportFragmentManager
        if (fm.findFragmentById(R.id.fragmentContainer) == null) {
            fm.beginTransaction().add(R.id.fragmentContainer, HotelsFragment()).commit()
        }

        displayHomeUpIfHasParent()
    }

    override fun navigateToHotelDetails(hotelId: Long) {
        startActivity(DetailsActivity.newIntent(this, hotelId))
    }
}