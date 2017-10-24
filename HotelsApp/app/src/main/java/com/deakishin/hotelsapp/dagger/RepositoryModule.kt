package com.deakishin.hotelsapp.dagger

import android.content.Context
import com.deakishin.hotelsapp.model.repository.Repository
import com.deakishin.hotelsapp.model.repository.RepositoryImpl
import com.deakishin.hotelsapp.model.repository.database.DatabaseService
import com.deakishin.hotelsapp.model.repository.database.room.DatabaseServiceRoomImpl
import com.deakishin.hotelsapp.model.repository.server.ServerService
import com.deakishin.hotelsapp.model.repository.server.rest.ServerServiceRetrofitImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(server: ServerService,
                          database: DatabaseService): Repository =
            RepositoryImpl(server, database)

    @Provides
    fun provideServerService(): ServerService =
            ServerServiceRetrofitImpl()
            //ServerServiceDummyImpl()

    @Provides
    @Singleton
    fun provideDatabaseService(context: Context): DatabaseService =
            DatabaseServiceRoomImpl(context)
}