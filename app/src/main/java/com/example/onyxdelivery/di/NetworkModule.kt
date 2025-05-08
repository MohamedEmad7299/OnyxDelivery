package com.example.onyxdelivery.di

import com.example.onyxdelivery.data.remote.api.OnyxDeliveryApiService
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
    fun provideRouteApiService(): OnyxDeliveryApiService {

        val baseURL = "http://mdev.yemensoft.net:8087/OnyxDeliveryService/Service.svc/"

        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(OnyxDeliveryApiService::class.java)
    }
}