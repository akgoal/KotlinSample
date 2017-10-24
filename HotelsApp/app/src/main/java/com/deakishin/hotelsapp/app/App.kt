package com.deakishin.hotelsapp.app

import android.app.Application
import com.deakishin.hotelsapp.dagger.AppComponent
import com.deakishin.hotelsapp.dagger.AppModule
import com.deakishin.hotelsapp.dagger.DaggerAppComponent

/**
 * Custom {@see Application} class for setting up dependency injection across the app.
 */
class App : Application() {

    /** AppComponent for injecting dependencies. */
    val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}