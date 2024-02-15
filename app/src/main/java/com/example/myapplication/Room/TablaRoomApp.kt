package com.example.myapplication.Room

import android.app.Application
import androidx.room.Room

class TablaRoomApp: Application () {
    val room = Room
        .databaseBuilder(this, TablaRoomDatos::class.java,"debilidades")
        .build()
}