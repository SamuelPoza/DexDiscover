package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.Room.TablaRoom
import com.example.myapplication.Room.TablaRoomViewModel
import com.example.myapplication.api.getCatsFact
import com.example.myapplication.baseDeDatos.BaseDeDatosViewModel
import com.example.myapplication.navigation.AppNavigation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private var catsFactCalled = false
    private lateinit var tablaRoomViewModel: TablaRoomViewModel
    private lateinit var baseDeDatosViewModel: BaseDeDatosViewModel

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tablaRoomViewModel = ViewModelProvider(this).get(TablaRoomViewModel::class.java)

        baseDeDatosViewModel = ViewModelProvider(this).get(BaseDeDatosViewModel::class.java)

        datosTablaSQL()
        datosTablaRoom()
        llamarCatsApi()

        // Aquí implementa el SharedPreferences

        val sharedPrefs = getSharedPreferences("Shared1", Context.MODE_PRIVATE)
        sharedPrefs.edit().putInt("key1", 1).apply()

        val savedShare = sharedPrefs.getInt("key1", 9)
        Log.d("Lyfecycle", "onCreate: $savedShare")

        setContent {
            // Llama al AppNavigation por temas de organización
            AppNavigation()
        }
    }

    private fun datosTablaSQL() {

        baseDeDatosViewModel.insertarTipo("Planta", "Veneno")
        baseDeDatosViewModel.insertarTipo("Fuego", "Agua")
        baseDeDatosViewModel.insertarTipo("Eléctrico", "Bicho")
        baseDeDatosViewModel.insertarTipo("Normal", "Volador")
        baseDeDatosViewModel.insertarTipo("Tierra", "Hada")
    }

    private fun datosTablaRoom() {
        val tiposAInsertar = listOf(
            TablaRoom(name = "Bulbasaur", debilidad1 = "Fuego", debilidad2 = "Hielo", debilidad3 = "Volador", debilidad4 = "Psiquico"),
            TablaRoom(name = "Ivysaur", debilidad1 = "Fuego", debilidad2 = "Hielo", debilidad3 = "Volador", debilidad4 = "Psiquico"),
            TablaRoom(name = "Venosaur", debilidad1 = "Fuego", debilidad2 = "Hielo", debilidad3 = "Volador", debilidad4 = "Psiquico"),
            TablaRoom(name = "Charmander", debilidad1 = "Agua", debilidad2 = "Tierra", debilidad3 = "Roca", debilidad4 = ""),
            TablaRoom(name = "Charmeleon", debilidad1 = "Agua", debilidad2 = "Tierra", debilidad3 = "Roca", debilidad4 = ""),
            TablaRoom(name = "Charizard", debilidad1 = "Agua", debilidad2 = "Eléctrico", debilidad3 = "Roca", debilidad4 = ""),
            TablaRoom(name = "Squirtle", debilidad1 = "Planta", debilidad2 = "Eléctrico", debilidad3 = "", debilidad4 = ""),
            TablaRoom(name = "Wartortle", debilidad1 = "Planta", debilidad2 = "Eléctrico", debilidad3 = "", debilidad4 = ""),
            TablaRoom(name = "Bastoise", debilidad1 = "Planta", debilidad2 = "Eléctrico", debilidad3 = "", debilidad4 = ""),
            TablaRoom(name = "Caterpie", debilidad1 = "Fuego", debilidad2 = "Volador", debilidad3 = "Roca", debilidad4 = ""),
            TablaRoom(name = "Metapod", debilidad1 = "Fuego", debilidad2 = "Volador", debilidad3 = "Roca", debilidad4 = ""),
            TablaRoom(name = "Butterfree", debilidad1 = "Fuego", debilidad2 = "Eléctrico", debilidad3 = "Hielo", debilidad4 = "Volador"),
            TablaRoom(name = "Weedle", debilidad1 = "Fuego", debilidad2 = "Volador", debilidad3 = "Psiquico", debilidad4 = "Roca"),
            TablaRoom(name = "Kakuna", debilidad1 = "Fuego", debilidad2 = "Volador", debilidad3 = "Psiquico", debilidad4 = "Roca"),
            TablaRoom(name = "Beedrill ", debilidad1 = "Fuego", debilidad2 = "Volador", debilidad3 = "Psiquico", debilidad4 = "Roca"),
            TablaRoom(name = "Pidgey ", debilidad1 = "Eléctrico", debilidad2 = "Hielo", debilidad3 = "Roca", debilidad4 = ""),
            TablaRoom(name = "Pidgeotto ", debilidad1 = "Eléctrico", debilidad2 = "Hielo", debilidad3 = "Roca", debilidad4 = ""),
            TablaRoom(name = "Pidgeot ", debilidad1 = "Eléctrico", debilidad2 = "Hielo", debilidad3 = "Roca", debilidad4 = ""),
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

        // Insertar los datos en la tabla Room
        tiposAInsertar.forEach { tipo ->
            tablaRoomViewModel.insertarTipo(tipo)
        }
    }

    private fun llamarCatsApi() {
        if (!catsFactCalled) {
            lifecycleScope.launch(Dispatchers.IO) {
                getCatsFact()
            }
            catsFactCalled = true
        }
    }
}

