package com.example.myapplication.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class FirstScreenViewModel @Inject constructor() : ViewModel() {

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







