package com.example.myapplication.data.sources.db.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TablaRoomDao {

    @Query("SELECT * FROM TablaRoom")
    fun getAll(): LiveData<List<TablaRoom>>

    @Query("SELECT * FROM TablaRoom WHERE name = :name")
    fun getByName(name: String): TablaRoom

    @Update
    fun update (tablaRom: TablaRoom)

    @Insert
    fun insert (debilidades: TablaRoom)

    @Delete
    fun delete (tablaRom: TablaRoom)


}
