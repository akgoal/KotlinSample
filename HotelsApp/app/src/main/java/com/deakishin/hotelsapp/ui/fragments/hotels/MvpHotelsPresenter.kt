package com.deakishin.hotelsapp.ui.fragments.hotels

import com.deakishin.hotelsapp.model.entities.Hotel
import com.deakishin.hotelsapp.model.repository.DataListener
import com.deakishin.hotelsapp.model.repository.Repository
import com.deakishin.hotelsapp.ui.mvp.MvpPresenter

/**
 * MVP Presenter that works with a view that displays list of hotels.
 * @param repository Repository to load hotels from.
 */
class MvpHotelsPresenter(private val repository: Repository) :
        MvpPresenter<MvpHotelsView>() {

    private val dataLoadingListener = object : DataListener<List<Hotel>> {
        override fun onDataLoading(lastData: List<Hotel>?) {
            handleDataLoadingStarted()
        }

        override fun onDataLoaded(loadedData: List<Hotel>?) {
            handleDataLoadingStopped()
            handleLoadedData(loadedData)
        }

        override fun onError(lastData: List<Hotel>?) {
            handleDataLoadingStopped()
            handleLoadingDataError()
        }
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
            it.setHotels(null)
            it.showHotelLoadingError(false)
            it.showNoHotelsMessage(false)
            it.showLoadingIndicator(false)
        }
    }

    /** Removes listener from the repository. */
    private fun unsubscribeFromRepository() {
        repository.removeHotelsListener(dataLoadingListener)
    }

    /** Loads data from the repository.
     * @param forceUpdate True if data must be updated from the server,
     * false otherwise.*/
    private fun loadData(forceUpdate: Boolean) {
        clearView()
        repository.loadHotels(forceUpdate, dataLoadingListener)
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
    private fun handleLoadedData(hotels: List<Hotel>?) {
        if (hotels == null || hotels.isEmpty()) {
            view?.showNoHotelsMessage(true)
        } else {
            view?.setHotels(hotels)
        }
    }

    /**
    * Invoked when data loading failed.
    */
    private fun handleLoadingDataError() {
        view?.showHotelLoadingError(true)
    }

    /**
     * Invoked when data refresh is issued.
     */
    fun onRefresh() {
        loadData(true)
    }

    /**
     * Invoked when a hotel is selected.
     */
    fun onHotelSelected(hotel: Hotel) {
        view?.showHotelDetails(hotel.id)
    }
}