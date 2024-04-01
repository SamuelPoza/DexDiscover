package com.example.myapplication.screen.first

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.myapplication.data.sources.db.Room.TablaRoomDatos
import com.example.myapplication.data.sources.db.SQLite.BaseDeDatos
import com.example.myapplication.data.sources.db.SQLite.BaseDeDatosHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class FirstScreenViewModel @Inject constructor() : ViewModel() {

    // Funcion para conseguir el tipo 1 de la tabla SQL
    fun consultarTipo1(context: Context, fila1: Int, columna1: Int, callback: (String) -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val dbHelper = BaseDeDatosHelper(context)
                val readableDB = dbHelper.readableDatabase

                val cursor = readableDB.rawQuery("SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET $fila1", null)

                cursor?.use { c ->
                    if (c.moveToFirst()) {
                        val primerValor = c.getString(columna1)
                        callback(primerValor)
                    }
                }

                cursor?.close()
            }
        }
    }

    // Funcion para conseguir el tipo 2 de la tabla SQL
    fun consultarTipo2(context: Context, fila1: Int, columna1: Int, callback: (String) -> Unit) {

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val dbHelper = BaseDeDatosHelper(context)
                val readableDB = dbHelper.readableDatabase

                val cursor = readableDB.rawQuery("SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET $fila1", null)

                cursor?.use { c ->
                    if (c.moveToFirst()) {
                        val primerValor = c.getString(columna1)
                        callback(primerValor)
                    }
                }

                cursor?.close()
            }
        }

    }

    // Funcion para conseguir las debilidades de la tabla Room
    fun consultarDebilidades(context: Context, nombre: String, callback: (String) -> Unit) {

        viewModelScope.launch {
            withContext(Dispatchers.IO) {

                val database = Room.databaseBuilder(
                    context,
                    TablaRoomDatos::class.java, "debilidades"
                ).build()

                val dao = database.tablaRoomDao()

                val pokemon = dao.getByName(nombre)
                callback("${pokemon?.debilidad1} ${pokemon?.debilidad2} ${pokemon?.debilidad3} ${pokemon?.debilidad4}")
            }

        }

    }

    // Funcion para conseguir el nombre de la tabla Room
    fun consultarNombre(context: Context, nombre: String, callback: (String) -> Unit) {

        callback(nombre)

    }

    // Propiedades internas para el estado
    private val _topAppBarTitle = mutableStateOf("DEXDISCOVER")
    private val _bottomAppBarButtonText = mutableStateOf("Tipos: ")
    private val _bottomAppBarButtonText2 = mutableStateOf("Debilidades: ")
    private val _bottomAppBarButtonText3 = mutableStateOf("Nombre: ")
    private val _imageState = mutableStateOf("https://cdn-icons-png.flaticon.com/128/287/287221.png")

    // Propiedades públicas para obtener el estado
    val topAppBarTitle get() = _topAppBarTitle.value
    val bottomAppBarButtonText get() = _bottomAppBarButtonText.value
    val bottomAppBarButtonText2 get() = _bottomAppBarButtonText2.value
    val bottomAppBarButtonText3 get() = _bottomAppBarButtonText3.value
    val imageState get() = _imageState.value

    // Setter para actualizar el estado automáticamente
    internal fun updateTopAppBarTitle(newTitle: String) {
        _topAppBarTitle.value = newTitle
    }

    internal fun updateBottomAppBarButtonText(newButtonText: String) {
        _bottomAppBarButtonText.value = newButtonText
    }

    internal fun updateBottomAppBarButtonText2(newButtonText: String) {
        _bottomAppBarButtonText2.value = newButtonText
    }

    internal fun updateBottomAppBarButtonText3(newButtonText: String) {
        _bottomAppBarButtonText3.value = newButtonText
    }

    internal fun cambiaImagen(newImageUrl: String) {
        _imageState.value = newImageUrl
    }

    // Variable global en el ViewModel
    val myGlobalVariable = mutableStateOf("hola")

    // Función para actualizar la variable global
    fun updateMyGlobalVariable(newValue: String) {
        myGlobalVariable.value = newValue
    }

    private var _state: List<Enlace> = emptyList()
    val state
        get() = _state

    val firstEnlace: Enlace?
        get() = _state.firstOrNull()

    init {
        _state = listOf(
            Enlace(
            name = "Funciona",

            ),

        )

    }

}

data class Enlace (
    val name:String,

)







