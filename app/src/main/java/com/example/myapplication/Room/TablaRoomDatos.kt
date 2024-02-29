package com.example.myapplication.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(

    entities = [TablaRoom::class],
    version = 1

)
abstract class TablaRoomDatos : RoomDatabase(){

    abstract fun tablaRoomDao(): TablaRoomDao

    //De aqui para abajo nuevo
    companion object {
        @Volatile
        private var INSTANCE: TablaRoomDatos? = null

        fun getInstance(context: Context): TablaRoomDatos {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TablaRoomDatos::class.java,
                    "debilidades"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}