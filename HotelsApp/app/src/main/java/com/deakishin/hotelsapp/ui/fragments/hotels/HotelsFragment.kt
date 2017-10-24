package com.deakishin.hotelsapp.ui.fragments.hotels

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.*
import com.deakishin.hotelsapp.R
import com.deakishin.hotelsapp.dagger.AppComponent
import com.deakishin.hotelsapp.model.entities.Hotel
import com.deakishin.hotelsapp.ui.components.BaseFragment
import com.deakishin.hotelsapp.utils.extensions.showOrGone
import kotlinx.android.synthetic.main.hotels_fragment.*
import javax.inject.Inject

/**
 * Fragment for displaying list of hotels.
 */
class HotelsFragment : BaseFragment(), MvpHotelsView {

    @Inject
    lateinit var presenter: MvpHotelsPresenter

    val hotelsAdapter = HotelsAdapter({ presenter.onHotelSelected(it) })

    override fun bindPresenter() {
        presenter.bindView(this)
    }

    override fun unbindPresenter() {
        presenter.unbindView()
    }

    override fun inject(component: AppComponent) {
        component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.hotels_fragment, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hotelsSwipeRefresh.setOnRefreshListener { presenter.onRefresh() }

        hotelsRecyclerView.layoutManager = GridLayoutManager(activity, 1)
        hotelsRecyclerView.adapter = hotelsAdapter
    }

    override fun setHotels(hotels: List<Hotel>?) {
        hotelsAdapter.data = hotels
    }

    override fun showLoadingIndicator(toShow: Boolean) {
        hotelsSwipeRefresh.isRefreshing = toShow
    }

    override fun showNoHotelsMessage(toShow: Boolean) {
        hotelsEmptyPanel.showOrGone(toShow)
    }

    override fun showHotelLoadingError(toShow: Boolean) {
        hotelsErrorPanel.showOrGone(toShow)
    }

    override fun showHotelDetails(hotelId: Long) {
        (activity as? HotelDetailsNavigationActivity)?.navigateToHotelDetails(hotelId)
    }

    override val menuResId = R.menu.hotels_fragment

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.hotels_menu_item_refresh -> {
                presenter.onRefresh()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}