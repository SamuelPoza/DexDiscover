package com.example.myapplication.baseDeDatos

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.room.Room
import com.example.myapplication.Room.TablaRoomDatos
import com.example.myapplication.screens.variableGlobalDebilidades
import com.example.myapplication.screens.variableGlobalNombre
import com.example.myapplication.screens.variableGlobalTipo1
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.lifecycle.viewModelScope
import com.example.myapplication.screens.variableGlobalTipo2


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
