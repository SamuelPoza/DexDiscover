package com.example.myapplication.Room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(

    entities = [TablaRoom::class],
    version = 1

)
abstract class TablaRoomDatos : RoomDatabase(){

    abstract fun tablaRoomDao(): TablaRoomDao

}