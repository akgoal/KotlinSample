package com.deakishin.hotelsapp.ui.components

import android.support.v7.widget.Toolbar

/** Interface for an activity that can manage its toolbar. */
interface ToolbarActivity {

    val toolbar: Toolbar

    fun setToolbarTitle(title: String?) {
        toolbar.title = title
    }
}