package com.deakishin.hotelsapp.ui.mvp

/**
 * Base MVP Presenter.
 *
 * @param T View that the presenter works with.
 */
abstract class MvpPresenter<T : MvpView> {

    /** MVP View that is bound to the presenter. */
    protected var view: T? = null
        private set(value) {
            field = value
        }

    /** Binds view to the presenter */
    fun bindView(mvpView: T) {
        view = mvpView
        onViewBound()
    }

    /** Unbinds view from the presenter. */
    fun unbindView() {
        view = null
        onViewUnbound()
    }

    /** Invoked when View is bound. */
    abstract fun onViewBound()

    /** Invoked when View is unbound. */
    abstract fun onViewUnbound()
}