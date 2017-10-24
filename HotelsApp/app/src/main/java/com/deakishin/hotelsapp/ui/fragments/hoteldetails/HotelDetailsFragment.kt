package com.deakishin.hotelsapp.ui.fragments.hoteldetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.deakishin.hotelsapp.R
import com.deakishin.hotelsapp.dagger.AppComponent
import com.deakishin.hotelsapp.model.entities.HotelDetails
import com.deakishin.hotelsapp.ui.components.BaseFragment
import com.deakishin.hotelsapp.utils.BorderCropTransformation
import com.deakishin.hotelsapp.utils.extensions.latLiteral
import com.deakishin.hotelsapp.utils.extensions.lonLiteral
import com.deakishin.hotelsapp.utils.extensions.showOrGone
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.hotel_details_fragment.*
import javax.inject.Inject

/** Fragment for displaying hotel details. */
class HotelDetailsFragment : BaseFragment(), MvpHotelDetailsView {

    companion object {
        private val ARG_HOTEL_ID = "hotelId"

        /** Creates a new instance of the fragment.
         * @param hotelId Id of the hotel whose details must be displayed. */
        fun newInstance(hotelId: Long): HotelDetailsFragment =
                HotelDetailsFragment().apply {
                    arguments = Bundle().apply { putLong(ARG_HOTEL_ID, hotelId) }
                }
    }

    @Inject
    lateinit var presenter: MvpHotelDetailsPresenter

    private var hotelId: Long = -1

    override fun bindPresenter() {
        presenter.bindView(this, hotelId)
    }

    override fun unbindPresenter() {
        presenter.unbindView()
    }

    override fun inject(component: AppComponent) {
        component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        arguments?.let { hotelId = it.getLong(ARG_HOTEL_ID, -1) }

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.hotel_details_fragment, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hotel_details_swiperefresh.setOnRefreshListener { presenter.onRefresh() }
    }

    override fun setHotelDetails(hotelDetails: HotelDetails?) {
        hotelDetails?.let {
            with(it) {
                hotel_details_info_panel.showOrGone(true)
                hotel_details_name_textView.text = getString(R.string.hotel_name, basicInfo.name)
                hotel_details_address_textView.text =
                        getString(R.string.hotel_address_advanced, basicInfo.address,
                                lat, latLiteral,
                                lon, lonLiteral)
                hotel_details_distance_textView.text = getString(R.string.hotel_distance, basicInfo.distance)
                hotel_details_suits_textView.text = getString(R.string.hotel_suits, basicInfo.availableSuitsCount)
                hotel_details_stars_textView.text = getString(R.string.hotel_stars, basicInfo.stars)

                if (imageUrl.isBlank()) {
                    hotel_details_image_progressBar.showOrGone(false)
                    hotel_details_image_empty_view.showOrGone(true)
                    hotel_details_image_imageView.setImageBitmap(null)
                } else {
                    hotel_details_image_progressBar.showOrGone(true)
                    hotel_details_image_empty_view.showOrGone(false)
                    Picasso.with(activity).load(imageUrl)
                            .transform(BorderCropTransformation(1))
                            .into(hotel_details_image_imageView, object : Callback {
                                override fun onSuccess() {
                                    hotel_details_image_progressBar?.showOrGone(false)
                                }

                                override fun onError() {
                                    hotel_details_image_progressBar?.showOrGone(false)
                                    hotel_details_image_empty_view?.showOrGone(true)
                                }
                            })
                }
            }
        } ?: hotel_details_info_panel.showOrGone(false)
    }

    override fun showLoadingIndicator(toShow: Boolean) {
        hotel_details_swiperefresh.isRefreshing = toShow
    }

    override fun showLoadingError(toShow: Boolean) {
        hotel_details_error_panel.showOrGone(toShow)
    }

    override val menuResId = R.menu.hotel_details_fragment

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.hotel_details_menu_item_refresh -> {
                presenter.onRefresh()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}