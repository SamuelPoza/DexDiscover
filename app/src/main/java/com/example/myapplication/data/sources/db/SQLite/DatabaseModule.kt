package com.example.myapplication.data.sources.db.SQLite

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object AppModule {

    @Provides
    fun provideBaseDeDatosRepositorio(context: Context): BaseDeDatosRepositorio {
        return BaseDeDatosRepositorio(context)
    }
}