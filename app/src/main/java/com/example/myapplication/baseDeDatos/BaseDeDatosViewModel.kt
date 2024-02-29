package com.example.myapplication.baseDeDatos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import android.database.Cursor


class BaseDeDatosViewModel(application: Application) : AndroidViewModel(application) {
    private val miRepositorio: BaseDeDatosRepositorio = BaseDeDatosRepositorio(application)

    fun insertarTipo(tipo1: String, tipo2: String) {
        miRepositorio.insertarTipo(tipo1, tipo2)
    }

    fun obtenerTodosLosTipos(): Cursor {
        return miRepositorio.obtenerTodosLosTipos()
    }

}
