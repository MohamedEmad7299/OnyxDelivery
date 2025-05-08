package com.example.onyxdelivery.di

import com.example.onyxdelivery.data.local.dao.DeliveryItemDao
import com.example.onyxdelivery.data.repository.CacheRepositoryImpl
import com.example.onyxdelivery.domain.repository.CacheRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheRepositoryModule {

    @Provides
    @Singleton
    fun provideCacheRepository(
        dao: DeliveryItemDao
    ): CacheRepository = CacheRepositoryImpl(dao)
}