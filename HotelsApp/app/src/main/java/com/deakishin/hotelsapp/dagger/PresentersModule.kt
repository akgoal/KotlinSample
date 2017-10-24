package com.deakishin.hotelsapp.dagger

import com.deakishin.hotelsapp.model.repository.Repository
import com.deakishin.hotelsapp.ui.fragments.hoteldetails.MvpHotelDetailsPresenter
import com.deakishin.hotelsapp.ui.fragments.hotels.MvpHotelsPresenter
import dagger.Module
import dagger.Provides

@Module
class PresentersModule {

    @Provides
    fun provideMvpHotelsPresenter(repo: Repository) =
            MvpHotelsPresenter(repo)

    @Provides
    fun provideMvpHotelDetailsPresenter(repo: Repository) =
            MvpHotelDetailsPresenter(repo)
}