package com.example.onyxdelivery.di

import android.content.Context
import com.example.onyxdelivery.data.local.session.PreferencesSessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SessionModule {

    @Provides
    @Singleton
    fun provideSessionManager(@ApplicationContext context: Context): PreferencesSessionManager {
        return PreferencesSessionManager(context)
    }
}