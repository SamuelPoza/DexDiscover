package com.example.myapplication.baseDeDatos

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class BaseDeDatosRepositorio(context: Context) {
    private val dbHelper: BaseDeDatosHelper = BaseDeDatosHelper(context)

    fun insertarTipo(tipo1: String, tipo2: String) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(BaseDeDatos.COLUMNA_TIPO_1, tipo1)
            put(BaseDeDatos.COLUMNA_TIPO_2, tipo2)
        }
        db.insert(BaseDeDatos.TABLA_NOMBRE, null, values)
    }

    // Por si hace falta en un futuro
    fun obtenerTodosLosTipos(): Cursor {
        val db = dbHelper.readableDatabase
        return db.rawQuery("SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE}", null)
    }

}
