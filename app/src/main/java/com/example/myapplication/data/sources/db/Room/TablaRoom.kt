package com.example.myapplication.data.sources.db.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TablaRoom (

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var name: String = "",
    var debilidad1: String = "",
    var debilidad2: String = "",
    var debilidad3: String = "",
    var debilidad4: String = "",

)



