package com.example.myapplication.data.sources.db.SQLite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import android.database.Cursor


class BaseDeDatosViewModel(application: Application) : AndroidViewModel(application) {
    private val miRepositorio: BaseDeDatosRepositorio = BaseDeDatosRepositorio(application)
    private val dbHelper: BaseDeDatosHelper = BaseDeDatosHelper(application)


    fun insertarTipo(tipo1: String, tipo2: String) {
        miRepositorio.insertarTipo(tipo1, tipo2)
    }

    // Por si hace falta en un futuro
    fun obtenerTodosLosTipos(): Cursor {
        return miRepositorio.obtenerTodosLosTipos()
    }

}
