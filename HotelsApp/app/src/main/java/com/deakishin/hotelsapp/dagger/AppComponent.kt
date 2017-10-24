package com.deakishin.hotelsapp.dagger

import com.deakishin.hotelsapp.app.App
import com.deakishin.hotelsapp.ui.fragments.hoteldetails.HotelDetailsFragment
import com.deakishin.hotelsapp.ui.fragments.hotels.HotelsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        PresentersModule::class,
        RepositoryModule::class)
)
interface AppComponent {
    fun inject(app: App)

    fun inject(hotelsFragment: HotelsFragment)
    fun inject(hotelDetailsFragment: HotelDetailsFragment)
}