package com.example.onyxdelivery.di

import com.example.onyxdelivery.domain.repository.CacheRepository
import com.example.onyxdelivery.domain.repository.OnyxRepository
import com.example.onyxdelivery.domain.usecase.GetCachedDeliveryItemsUseCase
import com.example.onyxdelivery.domain.usecase.GetDeliveryBillsItemsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GetDeliveryBillsModule {

    @Provides
    @Singleton
    fun provideGetDeliveryBillsItemsUseCase(
        repository: OnyxRepository
    ): GetDeliveryBillsItemsUseCase {
        return GetDeliveryBillsItemsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetCachedDeliveryItemsUseCase(
        cacheRepository: CacheRepository
    ): GetCachedDeliveryItemsUseCase {
        return GetCachedDeliveryItemsUseCase(cacheRepository)
    }
}