package com.example.myapplication.data.sources.db.Room

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTablaRoomRepositorio(application: Application): TablaRoomRepositorio {
        return TablaRoomRepositorio(application)
    }
}