package com.example.myapplication.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class FirstScreenViewModel @Inject constructor() : ViewModel() {

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







