package com.deakishin.hotelsapp.model.repository.dataloaders

import com.deakishin.hotelsapp.model.repository.DataListener
import com.deakishin.hotelsapp.utils.extensions.addIfNotContained
import com.deakishin.hotelsapp.utils.extensions.logError
import com.deakishin.hotelsapp.utils.extensions.removeElementAll
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Callable

/** Abstract class for a data loader.
 * The loader manages data loading and keeps loaded data in memory.
 *
 * @param T Type of loaded data. */
abstract class DataLoader<T> {

    /** Loads data from the server.
     * @return Data, loaded from the server.
     * @throws Exception if loading failed. */
    abstract protected fun loadServerData(): T?

    /** Loads cached data from the database.
     * @return Cached data, or null if there is none.*/
    abstract protected fun loadDbData(): T?

    /** Caches data in the local database.
     * @param data Data to cache. */
    abstract protected fun saveDataToDb(data: T?)

    /** Last successfully loaded data. */
    open protected var data: T? = null

    // Status of the last attempt to load data.
    private var dataStatus: DataStatus = DataStatus.EMPTY

    // Flag indicating that data is currently loading.
    private var loading: Boolean = false

    // Listeners to data loading events.
    private val listeners: MutableList<DataListener<T>> = mutableListOf()

    private var subscription: Disposable? = null

    /** Loads data.
     * @param forceUpdate Indicated whether data should be updated from the server
     * even if there is previously loaded data.
     * @param dataListener Listener to notify of data loading events.*/
    fun loadData(forceUpdate: Boolean, dataListener: DataListener<T>?) {

        dataListener?.onDataLoading(data)

        if (loading) {
            // Data is already loading so just add listener to it.
            addListener(dataListener)
            return
        }

        if (!forceUpdate) {
            if (dataStatus == DataStatus.ERROR) {
                // There was an error loading data
                // and data does not need to be updated.
                dataListener?.onError(data)
                return
            }

            if (dataStatus == DataStatus.READY) {
                // There is already loaded data
                // that does not need to be updated.
                dataListener?.onDataLoaded(data)
                return
            }
        }

        // Data is not loading so start loading it.
        loading = true
        addListener(dataListener)

        subscription = Single
                .fromCallable(Callable {
                    if (!forceUpdate) {
                        // Check if there is data cached in database.
                        loadDbData()?.let { return@Callable it }
                    }

                    // Either there is no data cached in database,
                    // or it must be updated from the network.
                    // So load data from the network.
                    loadServerData().also { saveDataToDb(it) }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ loadedData ->
                    onDataLoaded(true, loadedData)
                },
                        { e ->
                            logError(e)
                            onDataLoaded(false)
                        }
                )
    }

    /** Invoked when data is loaded.
     * @param success Indicates whether loading was successful or not.
     * @param loadedData Loaded data. If loading was not successful, this argument is ignored. */
    private fun onDataLoaded(success: Boolean, loadedData: T? = null) {
        loading = false
        dataStatus = if (success) {
            data = loadedData
            DataStatus.READY
        } else DataStatus.ERROR

        notifyListeners(true)
    }

    /** Notifies all listeners according to current status.
     * @param removeAfter Indicates whether listeners must be removed after getting notified. */
    private fun notifyListeners(removeAfter: Boolean = true) {
        listeners.forEach {
            when (dataStatus) {
                DataStatus.EMPTY -> it.onDataLoaded(null)
                DataStatus.READY -> it.onDataLoaded(data)
                DataStatus.ERROR -> it.onError(data)
            }
        }
        if (removeAfter) listeners.clear()
    }

    /** Stops data loading.
     * @return True if data was loading, false otherwise. */
    fun stopLoadingData(): Boolean {
        return if (loading) {
            loading = false
            subscription?.dispose()
            notifyListeners(true)
            true
        } else false
    }

    /** Removes listener from the data loader. */
    fun removeListener(listener: DataListener<T>) {
        listeners.removeElementAll(listener)
    }

    /** Adds new listener (if it is not already included). */
    private fun addListener(listener: DataListener<T>?) {
        listener?.let {
            listeners.addIfNotContained(it)
        }
    }
}

/** Status of data in a data loader. */
private enum class DataStatus {
    /** Data is not loaded. */
    EMPTY,
    /** Data is loaded and ready. */
    READY,
    /** Error when loading data. */
    ERROR
}