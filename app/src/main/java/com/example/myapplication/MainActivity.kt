package com.example.myapplication

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.lifecycleScope
import androidx.room.Room

import com.example.myapplication.Room.TablaRoom
import com.example.myapplication.Room.TablaRoomDatos
import com.example.myapplication.api.CatFactApi
import com.example.myapplication.api.getCatsFact

import com.example.myapplication.baseDeDatos.BaseDeDatos
import com.example.myapplication.baseDeDatos.BaseDeDatosHelper
import com.example.myapplication.navigation.AppNavigation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private var catsFactCalled = false


    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val dbHelper = BaseDeDatosHelper (this)
        val writableDB =  dbHelper.readableDatabase

        // Introduce valores en la base de datos SQL
        //Puede darse el caso en el que cuando ejecutas la aplicación, no aparecen las tablas en el app Inspection,
        //no se por que pero puede pasar

        val values = ContentValues().apply {
            put(BaseDeDatos.COLUMNA_TIPO_1, "Planta")
            put(BaseDeDatos.COLUMNA_TIPO_2, "Veneno")

        }

        writableDB.insert(BaseDeDatos.TABLA_NOMBRE, null, values)

        val values2 = ContentValues().apply {
            put(BaseDeDatos.COLUMNA_TIPO_1, "Fuego")
            put(BaseDeDatos.COLUMNA_TIPO_2, "Agua")
        }

        writableDB.insert(BaseDeDatos.TABLA_NOMBRE, null, values2)

        val values3 = ContentValues().apply {
            put(BaseDeDatos.COLUMNA_TIPO_1, "Eléctrico")
            put(BaseDeDatos.COLUMNA_TIPO_2, "Bicho")
        }

        writableDB.insert(BaseDeDatos.TABLA_NOMBRE, null, values3)

        val values4 = ContentValues().apply {
            put(BaseDeDatos.COLUMNA_TIPO_1, "Normal")
            put(BaseDeDatos.COLUMNA_TIPO_2, "Volador")
        }

        writableDB.insert(BaseDeDatos.TABLA_NOMBRE, null, values4)

        val values5 = ContentValues().apply {
            put(BaseDeDatos.COLUMNA_TIPO_1, "Tierra")
            put(BaseDeDatos.COLUMNA_TIPO_2, "Hada")
        }

        writableDB.insert(BaseDeDatos.TABLA_NOMBRE, null, values5)


        // Aqui doy valores a la base de datos Room
        //Puede darse el caso en el que cuando ejecutas la aplicación, no aparecen las tablas en el app Inspection,
        //no se por que pero puede pasar

        val database = Room.databaseBuilder(
            applicationContext,
            TablaRoomDatos::class.java, "debilidades"
        ).build()

        val dao = database.tablaRoomDao()

        lifecycleScope.launch(Dispatchers.IO) {

            dao.insert(TablaRoom(name = "Bulbasaur", debilidad1 = "Fuego", debilidad2 = "Hielo", debilidad3 = "Volador", debilidad4 = "Psiquico"))
            dao.insert(TablaRoom(name = "Ivysaur", debilidad1 = "Fuego", debilidad2 = "Hielo", debilidad3 = "Volador", debilidad4 = "Psiquico"))
            dao.insert(TablaRoom(name = "Venosaur", debilidad1 = "Fuego", debilidad2 = "Hielo", debilidad3 = "Volador", debilidad4 = "Psiquico"))
            dao.insert(TablaRoom(name = "Charmander", debilidad1 = "Agua", debilidad2 = "Tierra", debilidad3 = "Roca", debilidad4 = ""))
            dao.insert(TablaRoom(name = "Charmeleon", debilidad1 = "Agua", debilidad2 = "Tierra", debilidad3 = "Roca", debilidad4 = ""))
            dao.insert(TablaRoom(name = "Charizard", debilidad1 = "Agua", debilidad2 = "Eléctrico", debilidad3 = "Roca", debilidad4 = ""))
            dao.insert(TablaRoom(name = "Squirtle", debilidad1 = "Planta", debilidad2 = "Eléctrico", debilidad3 = "", debilidad4 = ""))
            dao.insert(TablaRoom(name = "Wartortle", debilidad1 = "Planta", debilidad2 = "Eléctrico", debilidad3 = "", debilidad4 = ""))
            dao.insert(TablaRoom(name = "Bastoise", debilidad1 = "Planta", debilidad2 = "Eléctrico", debilidad3 = "", debilidad4 = ""))
            dao.insert(TablaRoom(name = "Caterpie", debilidad1 = "Fuego", debilidad2 = "Volador", debilidad3 = "Roca", debilidad4 = ""))
            dao.insert(TablaRoom(name = "Metapod", debilidad1 = "Fuego", debilidad2 = "Volador", debilidad3 = "Roca", debilidad4 = ""))
            dao.insert(TablaRoom(name = "Butterfree", debilidad1 = "Fuego", debilidad2 = "Eléctrico", debilidad3 = "Hielo", debilidad4 = "Volador"))
            dao.insert(TablaRoom(name = "Weedle", debilidad1 = "Fuego", debilidad2 = "Volador", debilidad3 = "Psiquico", debilidad4 = "Roca"))
            dao.insert(TablaRoom(name = "Kakuna", debilidad1 = "Fuego", debilidad2 = "Volador", debilidad3 = "Psiquico", debilidad4 = "Roca"))
            dao.insert(TablaRoom(name = "Beedrill ", debilidad1 = "Fuego", debilidad2 = "Volador", debilidad3 = "Psiquico", debilidad4 = "Roca"))
            dao.insert(TablaRoom(name = "Pidgey ", debilidad1 = "Eléctrico", debilidad2 = "Hielo", debilidad3 = "Roca", debilidad4 = ""))
            dao.insert(TablaRoom(name = "Pidgeotto ", debilidad1 = "Eléctrico", debilidad2 = "Hielo", debilidad3 = "Roca", debilidad4 = ""))
            dao.insert(TablaRoom(name = "Pidgeot ", debilidad1 = "Eléctrico", debilidad2 = "Hielo", debilidad3 = "Roca", debilidad4 = ""))
            dao.insert(TablaRoom(name = "Rattata", debilidad1 = "Lucha", debilidad2 = "", debilidad3 = "", debilidad4 = ""))
            dao.insert(TablaRoom(name = "Raticate", debilidad1 = "Lucha", debilidad2 = "", debilidad3 = "", debilidad4 = ""))
            dao.insert(TablaRoom(name = "Spearrow", debilidad1 = "Eléctrico", debilidad2 = "Hielo", debilidad3 = "Roca", debilidad4 = ""))
            dao.insert(TablaRoom(name = "Fearow", debilidad1 = "Eléctrico", debilidad2 = "Hielo", debilidad3 = "Roca", debilidad4 = ""))
            dao.insert(TablaRoom(name = "Ekans", debilidad1 = "Tierra", debilidad2 = "Psiquico", debilidad3 = "", debilidad4 = ""))
            dao.insert(TablaRoom(name = "Arbok", debilidad1 = "Tierra", debilidad2 = "Psiquico", debilidad3 = "", debilidad4 = ""))
            dao.insert(TablaRoom(name = "Pikachu", debilidad1 = "Tierra", debilidad2 = "", debilidad3 = "", debilidad4 = ""))
            dao.insert(TablaRoom(name = "Raichu", debilidad1 = "Tierra", debilidad2 = "", debilidad3 = "", debilidad4 = ""))
            dao.insert(TablaRoom(name = "Sandshrew", debilidad1 = "Agua", debilidad2 = "Planta", debilidad3 = "Hielo", debilidad4 = ""))
            dao.insert(TablaRoom(name = "Sandslash", debilidad1 = "Agua", debilidad2 = "Planta", debilidad3 = "Hielo", debilidad4 = ""))

        }

        // Aqui tengo la llamada al CatsApi, lo meto en un if para que soo coja uno, a veces el fact esta en Ruso

        if (!catsFactCalled) {
            lifecycleScope.launch(Dispatchers.IO) {
                getCatsFact()
            }
            catsFactCalled = true
        }

        // Aqui implemento el sharedPreferences

        val sharedPrefs = this.getSharedPreferences("Shared1", Context.MODE_PRIVATE)
        sharedPrefs.edit().putInt("key1", 1).apply()

        val savedShare = sharedPrefs.getInt("key1", 9)
        Log.d("Lyfecycle", "onCreate: $savedShare")


        setContent {

            // Llamo al AppNavigation por temas de organización

             AppNavigation()

        }

    }


}




