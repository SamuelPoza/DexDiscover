package com.example.myapplication.data.sources.db.Room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TablaRoomViewModel(application: Application) : AndroidViewModel(application) {

    private val repositorio: TablaRoomRepositorio = TablaRoomRepositorio(application)

    fun insertarTipo(tipo: TablaRoom) {
        viewModelScope.launch(Dispatchers.IO) {
            repositorio.insertarTipo(tipo)
        }
    }

    // Por si hace falta en un futuro
    fun obtenerTodosLosTipos() = repositorio.obtenerTodosLosTipos()
}