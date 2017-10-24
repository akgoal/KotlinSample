package com.deakishin.hotelsapp.ui.components

import android.support.v4.app.NavUtils
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem

/**
 * Base activity for the app.
 *
 * Can display the Navigation Up button if there is a parent activity.
 */
abstract class BaseActivity : AppCompatActivity(), ToolbarActivity {

    /**
     * Displays the Navigate Up button on the Action Bar,
     * if there is a parent activity.
     *
     * If the button is displayed, its selection is handled automatically.
     *
     * This method must be invoked in the {@link #onCreate(Bundle)} method of the activity,
     * after setting content view and action bar.
     */
    protected fun displayHomeUpIfHasParent() {
        if (NavUtils.getParentActivityName(this) != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                if (NavUtils.getParentActivityName(this) != null) {
                    NavUtils.navigateUpFromSameTask(this)
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}