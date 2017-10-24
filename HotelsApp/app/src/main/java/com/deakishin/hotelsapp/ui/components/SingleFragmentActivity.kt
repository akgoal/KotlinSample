package com.deakishin.hotelsapp.ui.components

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import com.deakishin.hotelsapp.R
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Abstract class for an activity that hosts a single fragment.
 */
abstract class SingleFragmentActivity : BaseActivity() {

    /** Creates a fragment that the activity must host. */
    abstract fun createFragment(): Fragment

    override val toolbar: Toolbar by lazy { customToolbar }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_single_fragment)

        setSupportActionBar(toolbar)

        val fm = supportFragmentManager
        if (fm.findFragmentById(R.id.fragmentContainer) == null) {
            fm.beginTransaction().add(R.id.fragmentContainer, createFragment()).commit()
        }

        displayHomeUpIfHasParent()
    }
}