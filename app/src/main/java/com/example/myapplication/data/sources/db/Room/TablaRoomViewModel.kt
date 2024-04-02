package com.example.myapplication.data.sources.db.Room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TablaRoomViewModel @Inject constructor(
    application: Application,
    private val repositorio: TablaRoomRepositorio
) : AndroidViewModel(application) {

    fun insertarTipo(tipo: TablaRoom) {
        viewModelScope.launch(Dispatchers.IO) {
            repositorio.insertarTipo(tipo)
        }
    }

}

