package com.deakishin.hotelsapp.ui.components;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Base {@link SwipeRefreshLayout} that must be used across the app.
 * <p>
 * Fixes some bugs in the original class.
 */
public class CustomSwipeRefreshLayout extends SwipeRefreshLayout {
    private boolean mMeasured = false;
    private boolean mPreMeasureRefreshing = false;

    public CustomSwipeRefreshLayout(final Context context) {
        super(context);
    }

    public CustomSwipeRefreshLayout(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!mMeasured) {
            mMeasured = true;
            setRefreshing(mPreMeasureRefreshing);
        }
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        if (mMeasured) {
            super.setRefreshing(refreshing);
        } else {
            mPreMeasureRefreshing = refreshing;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            return super.onTouchEvent(event);
        } catch (IllegalArgumentException e) {
            return true;
        }
    }
}

