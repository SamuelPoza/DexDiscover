package com.example.myapplication.data.sources.db.SQLite

object BaseDeDatos {
    const val TABLA_NOMBRE = "Tipos"
    const val COLUMNA_TIPO_1 = "Tipo_1"
    const val COLUMNA_TIPO_2 = "Tipo_2"
}

const val SQL_CREATE_ENTRIES =
    """
    CREATE TABLE ${BaseDeDatos.TABLA_NOMBRE} (
        ${BaseDeDatos.COLUMNA_TIPO_1} TEXT PRIMARY KEY,
        ${BaseDeDatos.COLUMNA_TIPO_2} TEXT
    );
"""

