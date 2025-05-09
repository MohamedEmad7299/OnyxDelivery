package com.example.onyxdelivery.di

import com.example.onyxdelivery.data.remote.api.OnyxApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRouteApiService(): OnyxApiService {

        val baseURL = "https://mdev.yemensoft.net:473/OnyxDeliveryService/Service.svc/"

        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(OnyxApiService::class.java)
    }
}