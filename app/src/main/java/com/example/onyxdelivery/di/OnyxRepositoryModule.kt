package com.example.onyxdelivery.di

import com.example.onyxdelivery.data.remote.api.OnyxApiService
import com.example.onyxdelivery.data.repository.OnyxRepositoryImpl
import com.example.onyxdelivery.domain.repository.OnyxRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OnyxRepositoryModule {

    @Provides
    @Singleton
    fun provideOnyxRepository(
        onyxApiService: OnyxApiService
    ): OnyxRepository = OnyxRepositoryImpl(onyxApiService)
}

