package com.deakishin.hotelsapp.ui.components

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuInflater
import com.deakishin.hotelsapp.app.App
import com.deakishin.hotelsapp.dagger.AppComponent

/**
 * Base fragment for the app.
 */
abstract class BaseFragment : Fragment() {

    /** Binds MVP Presenter. */
    abstract fun bindPresenter()

    /** Unbinds MVP Presenter. */
    abstract fun unbindPresenter()

    /** Resource id for the options menu, or null, if there is no menu. */
    abstract val menuResId: Int?

    override fun onStart() {
        super.onStart()
        bindPresenter()
    }

    override fun onStop() {
        super.onStop()
        unbindPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity.application as? App)?.let {
            inject(it.component)
        }

        setHasOptionsMenu(menuResId != null)
    }

    /** Injects dependencies using AppComponent. */
    protected abstract fun inject(component: AppComponent)

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menuResId?.let { inflater?.inflate(it, menu) } ?:
                super.onCreateOptionsMenu(menu, inflater)
    }
}