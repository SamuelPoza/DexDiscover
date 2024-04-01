package com.example.myapplication.main


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.sources.db.Room.TablaRoom
import com.example.myapplication.data.sources.db.Room.TablaRoomViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(

    private val tablaRoomViewModel: TablaRoomViewModel
) : ViewModel() {

    fun insertarDatos() {
        val tiposAInsertar = listOf(
            TablaRoom(name = "Bulbasaur", debilidad1 = "Fuego", debilidad2 = "Hielo", debilidad3 = "Volador", debilidad4 = "Psiquico"),
            TablaRoom(name = "Ivysaur", debilidad1 = "Fuego", debilidad2 = "Hielo", debilidad3 = "Volador", debilidad4 = "Psiquico"),
            TablaRoom(name = "Venosaur", debilidad1 = "Fuego", debilidad2 = "Hielo", debilidad3 = "Volador", debilidad4 = "Psiquico"),
            TablaRoom(name = "Charmander", debilidad1 = "Agua", debilidad2 = "Tierra", debilidad3 = "Roca", debilidad4 = ""),
            TablaRoom(name = "Charmeleon", debilidad1 = "Agua", debilidad2 = "Tierra", debilidad3 = "Roca", debilidad4 = ""),
            TablaRoom(name = "Charizard", debilidad1 = "Agua", debilidad2 = "Eléctrico", debilidad3 = "Roca", debilidad4 = ""),
            TablaRoom(name = "Squirtle", debilidad1 = "Planta", debilidad2 = "Eléctrico", debilidad3 = "", debilidad4 = ""),
            TablaRoom(name = "Wartortle", debilidad1 = "Planta", debilidad2 = "Eléctrico", debilidad3 = "", debilidad4 = ""),
            TablaRoom(name = "Blastoise", debilidad1 = "Planta", debilidad2 = "Eléctrico", debilidad3 = "", debilidad4 = ""),
            TablaRoom(name = "Caterpie", debilidad1 = "Fuego", debilidad2 = "Volador", debilidad3 = "Roca", debilidad4 = ""),
            TablaRoom(name = "Metapod", debilidad1 = "Fuego", debilidad2 = "Volador", debilidad3 = "Roca", debilidad4 = ""),
            TablaRoom(name = "Butterfree", debilidad1 = "Fuego", debilidad2 = "Eléctrico", debilidad3 = "Hielo", debilidad4 = "Volador"),
            TablaRoom(name = "Weedle", debilidad1 = "Fuego", debilidad2 = "Volador", debilidad3 = "Psiquico", debilidad4 = "Roca"),
            TablaRoom(name = "Kakuna", debilidad1 = "Fuego", debilidad2 = "Volador", debilidad3 = "Psiquico", debilidad4 = "Roca"),
            TablaRoom(name = "Beedrill", debilidad1 = "Fuego", debilidad2 = "Volador", debilidad3 = "Psiquico", debilidad4 = "Roca"),
            TablaRoom(name = "Pidgey", debilidad1 = "Eléctrico", debilidad2 = "Hielo", debilidad3 = "Roca", debilidad4 = ""),
            TablaRoom(name = "Pidgeotto", debilidad1 = "Eléctrico", debilidad2 = "Hielo", debilidad3 = "Roca", debilidad4 = ""),
            TablaRoom(name = "Pidgeot", debilidad1 = "Eléctrico", debilidad2 = "Hielo", debilidad3 = "Roca", debilidad4 = ""),
            TablaRoom(name = "Rattata", debilidad1 = "Lucha", debilidad2 = "", debilidad3 = "", debilidad4 = ""),
            TablaRoom(name = "Raticate", debilidad1 = "Lucha", debilidad2 = "", debilidad3 = "", debilidad4 = ""),
            TablaRoom(name = "Spearrow", debilidad1 = "Eléctrico", debilidad2 = "Hielo", debilidad3 = "Roca", debilidad4 = ""),
            TablaRoom(name = "Fearow", debilidad1 = "Eléctrico", debilidad2 = "Hielo", debilidad3 = "Roca", debilidad4 = ""),
            TablaRoom(name = "Ekans", debilidad1 = "Tierra", debilidad2 = "Psiquico", debilidad3 = "", debilidad4 = ""),
            TablaRoom(name = "Arbok", debilidad1 = "Tierra", debilidad2 = "Psiquico", debilidad3 = "", debilidad4 = ""),
            TablaRoom(name = "Pikachu", debilidad1 = "Tierra", debilidad2 = "", debilidad3 = "", debilidad4 = ""),
            TablaRoom(name = "Raichu", debilidad1 = "Tierra", debilidad2 = "", debilidad3 = "", debilidad4 = ""),
            TablaRoom(name = "Sandshrew", debilidad1 = "Agua", debilidad2 = "Planta", debilidad3 = "Hielo", debilidad4 = ""),
            TablaRoom(name = "Sandslash", debilidad1 = "Agua", debilidad2 = "Planta", debilidad3 = "Hielo", debilidad4 = "")
        )

        viewModelScope.launch {
            tiposAInsertar.forEach { tipo ->
                tablaRoomViewModel.insertarTipo(tipo)
            }
        }
    }
}

