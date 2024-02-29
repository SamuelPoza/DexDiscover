package com.example.myapplication.Room

import android.content.Context
import androidx.lifecycle.LiveData

class TablaRoomRepositorio(context: Context) {

    private val dao: TablaRoomDao = TablaRoomDatos.getInstance(context).tablaRoomDao()

    fun insertarTipo(tipo: TablaRoom) {
        dao.insert(tipo)
    }

    fun obtenerTodosLosTipos(): LiveData<List<TablaRoom>> {
        return dao.getAll()
    }
}