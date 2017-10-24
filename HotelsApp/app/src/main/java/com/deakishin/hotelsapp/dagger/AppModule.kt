package com.deakishin.hotelsapp.dagger

import android.app.Application
import android.content.Context
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideContext():Context = application

    @Provides
    fun provideSharedPreferences(context: Context) =
            PreferenceManager.getDefaultSharedPreferences(context)
}