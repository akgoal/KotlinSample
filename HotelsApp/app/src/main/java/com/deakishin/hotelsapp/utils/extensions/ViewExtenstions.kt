package com.deakishin.hotelsapp.utils.extensions

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/** Returns View's context. */
val View.ctx: Context
    get() = context

/** Text color for the TextView. */
var TextView.textColor: Int
    get() = currentTextColor
    set(v) = setTextColor(v)

/** Makes the view visible or gone. */
fun View.showOrGone(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.GONE
}

/** Animates View to slide it off-screen to top. */
fun View.slideExit() {
    if (translationY == 0f) animate().translationY(-height.toFloat())
}

/** Animates View to slide it on-screen from top. */
fun View.slideEnter() {
    if (translationY < 0f) animate().translationY(0f)
}

/** Attaches Toolbar to the RecyclerView,
 * so that when the RecyclerView is scrolled, the Toolbar can slide on/off the screen. */
fun Toolbar.attachToScroll(recyclerView: RecyclerView) {
    recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recView: RecyclerView?, dx: Int, dy: Int) {
            if (dy > 0) slideExit() else slideEnter()
        }
    })
}

/** Inflates view with layout.
 * @param layoutResId Resource id for the layout.
 * @param attachToParent Indicates if the view has to be attached to the GroupView.
 * @return Inflated view.*/
fun ViewGroup.inflate(layoutResId: Int, attachToParent: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutResId, this, attachToParent)
}