package com.devgalan.tucofradia2.di

import android.content.Context
import com.devgalan.tucofradia2.core.data.Prefs
import com.devgalan.tucofradia2.core.data.StorageData
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class StorageModule {

    @Provides
    fun provideStorageData(@ApplicationContext context: Context): StorageData {
        return Prefs(context)
    }

    @Provides
    fun providesGson(): Gson {
        return Gson()
    }

}