package com.example.onyxdelivery.di

import android.content.Context
import androidx.room.Room
import com.example.onyxdelivery.data.local.dao.DeliveryItemDao
import com.example.onyxdelivery.data.local.db.OnyxDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): OnyxDatabase {
        return Room.databaseBuilder(
            context,
            OnyxDatabase::class.java,
            "onyx_database"
        ).build()
    }

    @Provides
    fun provideDeliveryItemDao(db: OnyxDatabase): DeliveryItemDao = db.deliveryItemDao()
}