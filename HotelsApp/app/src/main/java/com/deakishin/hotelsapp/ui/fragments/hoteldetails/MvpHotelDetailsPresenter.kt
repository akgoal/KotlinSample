package com.deakishin.hotelsapp.ui.fragments.hoteldetails

import com.deakishin.hotelsapp.model.entities.HotelDetails
import com.deakishin.hotelsapp.model.repository.DataListener
import com.deakishin.hotelsapp.model.repository.Repository
import com.deakishin.hotelsapp.ui.mvp.MvpPresenter

/**
 * MVP Presenter that works with a view that displays list of hotels.
 * @param repository Repository to load hotels from.
 */
class MvpHotelDetailsPresenter(private val repository: Repository) : MvpPresenter<MvpHotelDetailsView>() {

    private val dataLoadingListener = object : DataListener<HotelDetails> {
        override fun onDataLoading(lastData: HotelDetails?) {
            handleDataLoadingStarted()
        }

        override fun onDataLoaded(loadedData: HotelDetails?) {
            handleDataLoadingStopped()
            handleLoadedData(loadedData)
        }

        override fun onError(lastData: HotelDetails?) {
            handleDataLoadingStopped()
            handleLoadingDataError()
        }
    }

    private var hotelId: Long = -1

    /** Binds view to the presenter.
     * @param hotelId Id of the hotel. */
    fun bindView(mvpView: MvpHotelDetailsView, hotelId: Long) {
        this.hotelId = hotelId
        super.bindView(mvpView)
    }

    override fun onViewBound() {
        clearView()
        loadData(false)
    }

    override fun onViewUnbound() {
        unsubscribeFromRepository()
    }

    /** Clear all info in the view. */
    private fun clearView() {
        view?.let {
            it.setHotelDetails(null)
            it.showLoadingError(false)
            it.showLoadingIndicator(false)
        }
    }

    /** Removes listener from the repository. */
    private fun unsubscribeFromRepository() {
        repository.removeHotelDetailsListener(hotelId, dataLoadingListener)
    }

    /** Loads data from the repository.
     * @param forceUpdate True if data must be updated from the server,
     * false otherwise.*/
    private fun loadData(forceUpdate: Boolean) {
        clearView()
        repository.loadHotelDetails(hotelId, forceUpdate, dataLoadingListener)
    }

    /**
     * Invoked when data loading is started.
     */
    private fun handleDataLoadingStarted() {
        view?.showLoadingIndicator(true)
    }

    /**
     * Invoked when data loading is stopped.
     */
    private fun handleDataLoadingStopped() {
        view?.showLoadingIndicator(false)
    }

    /**
     * Handles data loaded from the repository.
     */
    private fun handleLoadedData(hotelDetails: HotelDetails?) {
        view?.let {
            if (hotelDetails == null) it.showLoadingError(true)
            else it.setHotelDetails(hotelDetails)
        }
    }

    /**
     * Invoked when data loading failed.
     */
    private fun handleLoadingDataError() {
        view?.showLoadingError(true)
    }

    /**
     * Invoked when data refresh is issued.
     */
    fun onRefresh() {
        loadData(true)
    }
}