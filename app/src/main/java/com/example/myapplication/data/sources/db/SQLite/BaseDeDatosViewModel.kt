package com.example.myapplication.data.sources.db.SQLite

import android.app.Application
import androidx.lifecycle.AndroidViewModel


class BaseDeDatosViewModel(application: Application) : AndroidViewModel(application) {
    private val miRepositorio: BaseDeDatosRepositorio = BaseDeDatosRepositorio(application)

    fun insertarTipo(tipo1: String, tipo2: String) {
        miRepositorio.insertarTipo(tipo1, tipo2)
    }

}
