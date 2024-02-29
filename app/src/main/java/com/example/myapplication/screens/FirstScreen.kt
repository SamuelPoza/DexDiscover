package com.example.myapplication.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.room.Room
import coil.compose.rememberImagePainter
import com.example.myapplication.R
import com.example.myapplication.Room.TablaRoomDatos
import com.example.myapplication.Room.TablaRoomViewModel
import com.example.myapplication.baseDeDatos.BaseDeDatos
import com.example.myapplication.baseDeDatos.BaseDeDatosHelper
import com.example.myapplication.baseDeDatos.BaseDeDatosViewModel
import com.example.myapplication.navigation.AppScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


// Creo variables globales

var variableGlobalTipo1 by mutableStateOf("")
var variableGlobalTipo2 by mutableStateOf("")
var variableGlobalDebilidad1 by mutableStateOf("")
var variableGlobalNombre by mutableStateOf("")


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "Range")
@Composable
fun FirstScreen(navController: NavController) {

    val viewModel: FirstScreenViewModel = viewModel() // Obtiene una instancia del ViewModel
    MyScaffoldFirstScreen(navController, viewModel)

}

//Creo un view model que exporta el estado de cada composable


@HiltViewModel
class MyViewModel @Inject constructor() : ViewModel() {

    // Propiedades internas para el estado

    private val _imageState = mutableStateOf("https://cdn-icons-png.flaticon.com/128/287/287221.png")

    // Propiedades públicas para obtener el estado

    val imageState get() = _imageState.value

    // Setter para actualizar el estado automáticamente

    internal fun cambiaImagen(newImageUrl: String) {
        _imageState.value = newImageUrl
    }
}



// Esta es mi Screen principal

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "Range")
@Composable
fun MyScaffoldFirstScreen(navController: NavController, viewModel: FirstScreenViewModel) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier,
                title = {
                    Row(
                        modifier = Modifier.background(Color(0xFF1A1A1A)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.menublanco),
                            contentDescription = "Icono filtro",
                            modifier = Modifier
                                .height(30.dp)
                                .width(30.dp)
                                .padding(4.dp),
                        )

                        Spacer(modifier = Modifier.width(25.dp))

                        val uiState = viewModel.imageState

                        Image(
                            painter = rememberImagePainter(uiState),
                            contentDescription = "Icono pokeball",
                            modifier = Modifier
                                .height(30.dp)
                                .width(30.dp)
                                .padding(4.dp)
                                .clickable {

                                    // Aqui le indico la imagen por la cual quiero que se cambie

                                    viewModel.cambiaImagen("https://cdn-icons-png.flaticon.com/128/1752/1752783.png")
                                },
                        )

                        Spacer(modifier = Modifier.width(0.dp))

                        // Aqui utilizo el viewModel.topAppBarTitle.value para poner el titulo

                        Text(
                            text = stringResource(id = R.string.nombreApp),
                            fontSize = 26.sp,
                            color = Color.White,
                            modifier = Modifier
                                .background(Color(0xFF1A1A1A))
                                .padding(8.dp),
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif
                        )

                        Spacer(modifier = Modifier.width(25.dp))

                        Image(
                            painter = painterResource(R.drawable.lupablanca),
                            contentDescription = "Icono busqueda",
                            modifier = Modifier
                                .height(30.dp)
                                .width(30.dp)
                                .padding(4.dp),
                        )
                        Spacer(modifier = Modifier.width(8.dp))

                        Image(
                            painter = painterResource(R.drawable.configuracionblanco),
                            contentDescription = "Icono configuracion",
                            modifier = Modifier
                                .height(35.dp)
                                .width(35.dp)
                                .padding(4.dp)
                                .clickable {
                                    navController.navigate(route = AppScreens.SecondScreen.route)
                                },
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(Color(0xFF1A1A1A))
            )
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .background(Color(0xFF1A1A1A))
                    .padding(13.dp)
                    .height(120.dp)
                    .fillMaxWidth(),
            ) {
                Box(
                    modifier = Modifier
                        .background(Color(0xFF1A1A1A))
                        .width(400.dp)
                ) {
                    Column {
                        Text(
                            text = stringResource(id = R.string.nombre) + variableGlobalNombre,
                            fontSize = 15.sp,
                            color = Color.White,
                            modifier = Modifier
                                .background(Color(0xFF1A1A1A))
                                .padding(8.dp),
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif
                        )

                        Text(
                            text = stringResource(id = R.string.tipos) + variableGlobalTipo1 + variableGlobalTipo2,
                            fontSize = 15.sp,
                            color = Color.White,
                            modifier = Modifier
                                .background(Color(0xFF1A1A1A))
                                .padding(8.dp),
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif
                        )

                        Text(
                            text = stringResource(id = R.string.debilidades) + variableGlobalDebilidad1,
                            fontSize = 15.sp,
                            color = Color.White,
                            modifier = Modifier
                                .background(Color(0xFF1A1A1A))
                                .padding(8.dp),
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            // Botón flotante, si es necesario
            // ...
        }
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .background(Color(0xFF333333))
                .fillMaxSize()
                .padding(7.dp)
        ) {
            val uiState = viewModel.imageState

            item {

                Box(
                    modifier = Modifier.background(Color(0xFF333333))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(15.dp))

                ) {

                    Image(
                        painter = painterResource(R.drawable.colorfondo),
                        contentDescription = "Imagen colorfondo",
                        modifier = Modifier
                            .height(49.dp)
                            .width(120.dp)
                            .background(Color(0xFF333333))


                            .clickable {

                            },
                    )
                }
            }

            item {

                Box(
                    modifier = Modifier.background(Color(0xFF333333))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(15.dp))

                ) {

                    Image(
                        painter = painterResource(R.drawable.colorfondo),
                        contentDescription = "Imagen colorfondo",
                        modifier = Modifier
                            .height(49.dp)
                            .width(120.dp)
                            .background(Color(0xFF333333))

                            .clickable {

                            },
                    )
                }
            }

            item {

                Box(
                    modifier = Modifier.background(Color(0xFF333333))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(15.dp))

                ) {

                    Image(
                        painter = painterResource(R.drawable.colorfondo),
                        contentDescription = "Imagen colorfondo",
                        modifier = Modifier
                            .height(49.dp)
                            .width(120.dp)
                            .background(Color(0xFF333333))

                            .clickable {

                            },
                    )
                }
            }

            item {

                Box(
                    modifier = Modifier.background(Color(0xFF333333))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(15.dp))

                ) {

                    Image(
                        painter = painterResource(R.drawable.bulbasaur),
                        contentDescription = "Imagen Bulbasaur",
                        modifier = Modifier
                            .height(110.dp)
                            .width(120.dp)
                            .background(Color(0xFFBDFCC9))


                            .clickable {

                                // Recibe los valores de la tabla SQL y los paso a una variable global

                                coroutineScope.launch {
                                    withContext(Dispatchers.IO) {
                                        val dbHelper = BaseDeDatosHelper(context)
                                        val readableDB = dbHelper.readableDatabase
                                        val cursor = readableDB.rawQuery(
                                            "SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 0",
                                            null
                                        ) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor =
                                                    c.getString(0) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo1 = primerValor

                                            }
                                        }

                                        cursor?.close()

                                        val cursor2 = readableDB.rawQuery(
                                            "SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 0",
                                            null
                                        ) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor2?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor =
                                                    c.getString(1) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo2 = ", " + primerValor
                                            }
                                        }

                                        cursor2?.close()

                                        // Recibe los datos de la tabla Room
                                        // Solo se lo implemente a algunos porque aun no tengo clara la interfaz/función que va a tener al final

                                        val database = Room.databaseBuilder(
                                            context,
                                            TablaRoomDatos::class.java, "debilidades"
                                        ).build()

                                        val dao = database.tablaRoomDao()

                                        val pokemonName = "Bulbasaur"
                                        val pokemon = dao.getById(1)
                                        println("La debilidad 1 de $pokemonName es: ${pokemon?.debilidad1}")
                                        variableGlobalDebilidad1 =
                                            "${pokemon?.debilidad1}, ${pokemon?.debilidad2}, ${pokemon?.debilidad3}, ${pokemon?.debilidad4}"
                                        variableGlobalNombre = pokemonName
                                    }
                                }

                            },
                    )
                }
            }

            item {

                Box(
                    modifier = Modifier.background(Color(0xFF333333))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(15.dp))

                ) {

                    Image(
                        painter = painterResource(R.drawable.ivysaur),
                        contentDescription = "Imagen Ivysaur",
                        modifier = Modifier
                            .height(110.dp)
                            .width(120.dp)
                            .background(Color(0xFFBDFCC9))

                            .clickable {

                                coroutineScope.launch {
                                    withContext(Dispatchers.IO) {

                                        val dbHelper = BaseDeDatosHelper(context)
                                        val readableDB = dbHelper.readableDatabase
                                        val cursor = readableDB.rawQuery("SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 0", null) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor = c.getString(0) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo1 = primerValor

                                            }
                                        }

                                        cursor?.close()

                                        val cursor2 = readableDB.rawQuery("SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 0", null) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor2?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor = c.getString(1) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo2 = ", " + primerValor
                                            }
                                        }

                                        cursor2?.close()

                                        val database = Room.databaseBuilder(
                                            context,
                                            TablaRoomDatos::class.java, "debilidades"
                                        ).build()

                                        val dao = database.tablaRoomDao()

                                        val pokemonName = "Ivysaur"
                                        val pokemon = dao.getById(2)
                                        println("La debilidad 1 de $pokemonName es: ${pokemon?.debilidad1}")
                                        variableGlobalDebilidad1 = "${pokemon?.debilidad1}, ${pokemon?.debilidad2}, ${pokemon?.debilidad3}, ${pokemon?.debilidad4}"
                                        variableGlobalNombre = pokemonName
                                    }
                                }
                            },
                    )
                }
            }

            item {

                Box(
                    modifier = Modifier.background(Color(0xFF333333))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(15.dp))

                ) {

                    Image(
                        painter = painterResource(R.drawable.venosaur),
                        contentDescription = "Imagen Venosaur",
                        modifier = Modifier
                            .height(110.dp)
                            .width(120.dp)
                            .background(Color(0xFFBDFCC9))

                            .clickable {

                                coroutineScope.launch {
                                    withContext(Dispatchers.IO) {
                                        val dbHelper = BaseDeDatosHelper(context)
                                        val readableDB = dbHelper.readableDatabase
                                        val cursor = readableDB.rawQuery("SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 0", null) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor = c.getString(0) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo1 = primerValor

                                            }
                                        }

                                        cursor?.close()

                                        val cursor2 = readableDB.rawQuery("SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 0", null) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor2?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor = c.getString(1) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo2 = ", " + primerValor
                                            }
                                        }

                                        cursor2?.close()

                                        val database = Room.databaseBuilder(
                                            context,
                                            TablaRoomDatos::class.java, "debilidades"
                                        ).build()

                                        val dao = database.tablaRoomDao()

                                        val pokemonName = "Venosaur"
                                        val pokemon = dao.getById(3)
                                        println("La debilidad 1 de $pokemonName es: ${pokemon?.debilidad1}")
                                        variableGlobalDebilidad1 = "${pokemon?.debilidad1}, ${pokemon?.debilidad2}, ${pokemon?.debilidad3}, ${pokemon?.debilidad4}"
                                        variableGlobalNombre = pokemonName
                                    }
                                }
                            },
                    )
                }
            }
            item {

                Box(
                    modifier = Modifier.background(Color(0xFF333333))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(15.dp))

                ) {

                    Image(
                        painter = painterResource(R.drawable.charmander),
                        contentDescription = "Imagen Charmander",
                        modifier = Modifier
                            .height(110.dp)
                            .width(120.dp)
                            .background(Color(0xFFFFB9C0))

                            .clickable {

                                coroutineScope.launch {
                                    withContext(Dispatchers.IO) {

                                        val dbHelper = BaseDeDatosHelper(context)
                                        val readableDB = dbHelper.readableDatabase
                                        val cursor = readableDB.rawQuery("SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 1", null) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor = c.getString(0) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo1 = primerValor
                                                variableGlobalTipo2 = ""

                                            }
                                        }

                                        cursor?.close()

                                        val database = Room.databaseBuilder(
                                            context,
                                            TablaRoomDatos::class.java, "debilidades"
                                        ).build()

                                        val dao = database.tablaRoomDao()

                                        val pokemonName = "Charmander"
                                        val pokemon = dao.getById(4)
                                        println("La debilidad 1 de $pokemonName es: ${pokemon?.debilidad1}")
                                        variableGlobalDebilidad1 = "${pokemon?.debilidad1}, ${pokemon?.debilidad2}, ${pokemon?.debilidad3} ${pokemon?.debilidad4}"
                                        variableGlobalNombre = pokemonName
                                    }
                                }
                            },
                    )
                }
            }

            item {

                Box(
                    modifier = Modifier.background(Color(0xFF333333))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(15.dp))

                ) {

                    Image(
                        painter = painterResource(R.drawable.charmeleon),
                        contentDescription = "Imagen Charmeleon",
                        modifier = Modifier
                            .height(110.dp)
                            .width(120.dp)
                            .background(Color(0xFFFFB9C0))

                            .clickable {

                                coroutineScope.launch {
                                    withContext(Dispatchers.IO) {
                                        val dbHelper = BaseDeDatosHelper(context)
                                        val readableDB = dbHelper.readableDatabase
                                        val cursor = readableDB.rawQuery("SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 1", null) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor = c.getString(0) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo1 = primerValor
                                                variableGlobalTipo2 = ""

                                            }
                                        }

                                        cursor?.close()

                                        val database = Room.databaseBuilder(
                                            context,
                                            TablaRoomDatos::class.java, "debilidades"
                                        ).build()

                                        val dao = database.tablaRoomDao()

                                        val pokemonName = "Charmeleon"
                                        val pokemon = dao.getById(5)
                                        println("La debilidad 1 de $pokemonName es: ${pokemon?.debilidad1}")
                                        variableGlobalDebilidad1 = "${pokemon?.debilidad1}, ${pokemon?.debilidad2}, ${pokemon?.debilidad3} ${pokemon?.debilidad4}"
                                        variableGlobalNombre = pokemonName
                                    }
                                }
                            },
                    )
                }
            }

            item {

                Box(
                    modifier = Modifier.background(Color(0xFF333333))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(15.dp))

                ) {

                    Image(
                        painter = painterResource(R.drawable.charizard),
                        contentDescription = "Imagen Charizard",
                        modifier = Modifier
                            .height(110.dp)
                            .width(120.dp)
                            .background(Color(0xFFFFB9C0))

                            .clickable {

                                coroutineScope.launch {
                                    withContext(Dispatchers.IO) {
                                        val dbHelper = BaseDeDatosHelper(context)
                                        val readableDB = dbHelper.readableDatabase
                                        val cursor = readableDB.rawQuery(
                                            "SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 1",
                                            null
                                        ) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor =
                                                    c.getString(0) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo1 = primerValor

                                            }
                                        }

                                        cursor?.close()

                                        val cursor2 = readableDB.rawQuery(
                                            "SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 3",
                                            null
                                        ) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor2?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor =
                                                    c.getString(1) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo2 = ", " + primerValor
                                            }
                                        }

                                        cursor2?.close()

                                        val database = Room.databaseBuilder(
                                            context,
                                            TablaRoomDatos::class.java, "debilidades"
                                        ).build()

                                        val dao = database.tablaRoomDao()

                                        val pokemonName = "Charizard"
                                        val pokemon = dao.getById(6)
                                        println("La debilidad 1 de $pokemonName es: ${pokemon?.debilidad1}")
                                        variableGlobalDebilidad1 = "${pokemon?.debilidad1}, ${pokemon?.debilidad2}, ${pokemon?.debilidad3} ${pokemon?.debilidad4}"
                                        variableGlobalNombre = pokemonName
                                    }
                                }
                            },
                    )
                }
            }

            item {

                Box(
                    modifier = Modifier.background(Color(0xFF333333))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(15.dp))

                ) {

                    Image(
                        painter = painterResource(R.drawable.squirtle),
                        contentDescription = "Imagen Squirtle",
                        modifier = Modifier
                            .height(110.dp)
                            .width(120.dp)
                            .background(Color(0xFFADD8E6))

                            .clickable {

                                coroutineScope.launch {
                                    withContext(Dispatchers.IO) {

                                        val dbHelper = BaseDeDatosHelper(context)
                                        val readableDB = dbHelper.readableDatabase
                                        val cursor = readableDB.rawQuery("SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 1", null) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor = c.getString(1) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo1 = primerValor
                                                variableGlobalTipo2 = ""

                                            }
                                        }

                                        cursor?.close()

                                        val database = Room.databaseBuilder(
                                            context,
                                            TablaRoomDatos::class.java, "debilidades"
                                        ).build()

                                        val dao = database.tablaRoomDao()

                                        val pokemonName = "Squirtle"
                                        val pokemon = dao.getById(7)
                                        println("La debilidad 1 de $pokemonName es: ${pokemon?.debilidad1}")
                                        variableGlobalDebilidad1 = "${pokemon?.debilidad1}, ${pokemon?.debilidad2} ${pokemon?.debilidad3} ${pokemon?.debilidad4}"
                                        variableGlobalNombre = pokemonName
                                    }
                                }

                            },
                    )
                }
            }

            item {

                Box(
                    modifier = Modifier.background(Color(0xFF333333))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(15.dp))

                ) {

                    Image(
                        painter = painterResource(R.drawable.wartortle),
                        contentDescription = "Imagen Wartortle",
                        modifier = Modifier
                            .height(110.dp)
                            .width(120.dp)
                            .background(Color(0xFFADD8E6))

                            .clickable {

                                coroutineScope.launch {
                                    withContext(Dispatchers.IO) {

                                        val dbHelper = BaseDeDatosHelper(context)
                                        val readableDB = dbHelper.readableDatabase
                                        val cursor = readableDB.rawQuery("SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 1", null) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor = c.getString(1) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo1 = primerValor
                                                variableGlobalTipo2 = ""

                                            }
                                        }

                                        cursor?.close()

                                        val database = Room.databaseBuilder(
                                            context,
                                            TablaRoomDatos::class.java, "debilidades"
                                        ).build()

                                        val dao = database.tablaRoomDao()

                                        val pokemonName = "Wartortle"
                                        val pokemon = dao.getById(8)
                                        println("La debilidad 1 de $pokemonName es: ${pokemon?.debilidad1}")
                                        variableGlobalDebilidad1 = "${pokemon?.debilidad1}, ${pokemon?.debilidad2} ${pokemon?.debilidad3} ${pokemon?.debilidad4}"
                                        variableGlobalNombre = pokemonName
                                    }
                                }

                            },
                    )
                }
            }

            item {

                Box(
                    modifier = Modifier.background(Color(0xFF333333))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(15.dp))

                ) {

                    Image(
                        painter = painterResource(R.drawable.blastoise),
                        contentDescription = "Imagen Blastoise",
                        modifier = Modifier
                            .height(110.dp)
                            .width(120.dp)
                            .background(Color(0xFFADD8E6))

                            .clickable {

                                coroutineScope.launch {
                                    withContext(Dispatchers.IO) {

                                        val dbHelper = BaseDeDatosHelper(context)
                                        val readableDB = dbHelper.readableDatabase
                                        val cursor = readableDB.rawQuery("SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 1", null) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor = c.getString(1) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo1 = primerValor
                                                variableGlobalTipo2 = ""

                                            }
                                        }

                                        cursor?.close()

                                        val database = Room.databaseBuilder(
                                            context,
                                            TablaRoomDatos::class.java, "debilidades"
                                        ).build()

                                        val dao = database.tablaRoomDao()

                                        val pokemonName = "Blastoise"
                                        val pokemon = dao.getById(9)
                                        println("La debilidad 1 de $pokemonName es: ${pokemon?.debilidad1}")
                                        variableGlobalDebilidad1 = "${pokemon?.debilidad1}, ${pokemon?.debilidad2} ${pokemon?.debilidad3} ${pokemon?.debilidad4}"
                                        variableGlobalNombre = pokemonName
                                    }
                                }
                            },
                    )
                }
            }

            item {

                Box(
                    modifier = Modifier.background(Color(0xFF333333))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(15.dp))

                ) {

                    Image(
                        painter = painterResource(R.drawable.caterpie),
                        contentDescription = "Imagen Caterpie",
                        modifier = Modifier
                            .height(110.dp)
                            .width(120.dp)
                            .background(Color(0xFFC4FFB9))

                            .clickable {

                                coroutineScope.launch {
                                    withContext(Dispatchers.IO) {
                                        val dbHelper = BaseDeDatosHelper(context)
                                        val readableDB = dbHelper.readableDatabase
                                        val cursor = readableDB.rawQuery("SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 2", null) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor = c.getString(1) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo1 = primerValor
                                                variableGlobalTipo2 = ""

                                            }
                                        }

                                        cursor?.close()

                                        val database = Room.databaseBuilder(
                                            context,
                                            TablaRoomDatos::class.java, "debilidades"
                                        ).build()

                                        val dao = database.tablaRoomDao()

                                        val pokemonName = "Caterpie"
                                        val pokemon = dao.getById(10)
                                        println("La debilidad 1 de $pokemonName es: ${pokemon?.debilidad1}")
                                        variableGlobalDebilidad1 = "${pokemon?.debilidad1}, ${pokemon?.debilidad2}, ${pokemon?.debilidad3} ${pokemon?.debilidad4}"
                                        variableGlobalNombre = pokemonName
                                    }
                                }
                            },
                    )
                }
            }

            item {

                Box(
                    modifier = Modifier.background(Color(0xFF333333))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(15.dp))

                ) {

                    Image(
                        painter = painterResource(R.drawable.metapod),
                        contentDescription = "Imagen Metapod",
                        modifier = Modifier
                            .height(110.dp)
                            .width(120.dp)
                            .background(Color(0xFFC4FFB9))

                            .clickable {

                                coroutineScope.launch {
                                    withContext(Dispatchers.IO) {
                                        val dbHelper = BaseDeDatosHelper(context)
                                        val readableDB = dbHelper.readableDatabase
                                        val cursor = readableDB.rawQuery("SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 2", null) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor = c.getString(1) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo1 = primerValor
                                                variableGlobalTipo2 = ""

                                            }
                                        }

                                        cursor?.close()

                                        val database = Room.databaseBuilder(
                                            context,
                                            TablaRoomDatos::class.java, "debilidades"
                                        ).build()

                                        val dao = database.tablaRoomDao()

                                        val pokemonName = "Metapod"
                                        val pokemon = dao.getById(11)
                                        println("La debilidad 1 de $pokemonName es: ${pokemon?.debilidad1}")
                                        variableGlobalDebilidad1 = "${pokemon?.debilidad1}, ${pokemon?.debilidad2}, ${pokemon?.debilidad3} ${pokemon?.debilidad4}"
                                        variableGlobalNombre = pokemonName
                                    }
                                }
                            },
                    )
                }
            }

            item {

                Box(
                    modifier = Modifier.background(Color(0xFF333333))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(15.dp))

                ) {

                    Image(
                        painter = painterResource(R.drawable.butterfree),
                        contentDescription = "Imagen Butterfree",
                        modifier = Modifier
                            .height(110.dp)
                            .width(120.dp)
                            .background(Color(0xFFC4FFB9))

                            .clickable {

                                coroutineScope.launch {
                                    withContext(Dispatchers.IO) {
                                        val dbHelper = BaseDeDatosHelper(context)
                                        val readableDB = dbHelper.readableDatabase
                                        val cursor = readableDB.rawQuery(
                                            "SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 2",
                                            null
                                        ) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor =
                                                    c.getString(1) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo1 = primerValor

                                            }
                                        }

                                        cursor?.close()

                                        val cursor2 = readableDB.rawQuery(
                                            "SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 3",
                                            null
                                        ) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor2?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor =
                                                    c.getString(1) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo2 = ", " + primerValor
                                            }
                                        }

                                        cursor2?.close()

                                        val database = Room.databaseBuilder(
                                            context,
                                            TablaRoomDatos::class.java, "debilidades"
                                        ).build()

                                        val dao = database.tablaRoomDao()

                                        val pokemonName = "Butterfree"
                                        val pokemon = dao.getById(12)
                                        println("La debilidad 1 de $pokemonName es: ${pokemon?.debilidad1}")
                                        variableGlobalDebilidad1 = "${pokemon?.debilidad1}, ${pokemon?.debilidad2}, ${pokemon?.debilidad3}, ${pokemon?.debilidad4}"
                                        variableGlobalNombre = pokemonName
                                    }
                                }
                            },
                    )
                }
            }

            item {

                Box(
                    modifier = Modifier.background(Color(0xFF333333))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(15.dp))

                ) {

                    Image(
                        painter = painterResource(R.drawable.weedle),
                        contentDescription = "Imagen Weedle",
                        modifier = Modifier
                            .height(110.dp)
                            .width(120.dp)
                            .background(Color(0xFFC4FFB9))

                            .clickable {

                                coroutineScope.launch {
                                    withContext(Dispatchers.IO) {

                                        val dbHelper = BaseDeDatosHelper(context)
                                        val readableDB = dbHelper.readableDatabase
                                        val cursor = readableDB.rawQuery("SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 0", null) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor = c.getString(0) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo1 = primerValor

                                            }
                                        }

                                        cursor?.close()

                                        val cursor2 = readableDB.rawQuery("SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 2", null) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor2?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor = c.getString(1) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo2 = ", " + primerValor
                                            }
                                        }

                                        cursor2?.close()

                                        val database = Room.databaseBuilder(
                                            context,
                                            TablaRoomDatos::class.java, "debilidades"
                                        ).build()

                                        val dao = database.tablaRoomDao()

                                        val pokemonName = "Weedle"
                                        val pokemon = dao.getById(13)
                                        println("La debilidad 1 de $pokemonName es: ${pokemon?.debilidad1}")
                                        variableGlobalDebilidad1 = "${pokemon?.debilidad1}, ${pokemon?.debilidad2}, ${pokemon?.debilidad3}, ${pokemon?.debilidad4}"
                                        variableGlobalNombre = pokemonName
                                    }
                                }
                            },
                    )
                }
            }

            item {

                Box(
                    modifier = Modifier.background(Color(0xFF333333))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(15.dp))

                ) {

                    Image(
                        painter = painterResource(R.drawable.kakuna),
                        contentDescription = "Imagen Kakuna",
                        modifier = Modifier
                            .height(110.dp)
                            .width(120.dp)
                            .background(Color(0xFFC4FFB9))

                            .clickable {

                                coroutineScope.launch {
                                    withContext(Dispatchers.IO) {

                                        val dbHelper = BaseDeDatosHelper(context)
                                        val readableDB = dbHelper.readableDatabase
                                        val cursor = readableDB.rawQuery("SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 2", null) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor = c.getString(0) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo1 = primerValor

                                            }
                                        }

                                        cursor?.close()

                                        val cursor2 = readableDB.rawQuery("SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 0", null) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor2?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor = c.getString(1) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo2 = ", " + primerValor
                                            }
                                        }

                                        cursor2?.close()

                                        val database = Room.databaseBuilder(
                                            context,
                                            TablaRoomDatos::class.java, "debilidades"
                                        ).build()

                                        val dao = database.tablaRoomDao()

                                        val pokemonName = "Kakuna"
                                        val pokemon = dao.getById(14)
                                        println("La debilidad 1 de $pokemonName es: ${pokemon?.debilidad1}")
                                        variableGlobalDebilidad1 = "${pokemon?.debilidad1}, ${pokemon?.debilidad2}, ${pokemon?.debilidad3}, ${pokemon?.debilidad4}"
                                        variableGlobalNombre = pokemonName
                                    }
                                }
                            },
                    )
                }
            }

            item {

                Box(
                    modifier = Modifier.background(Color(0xFF333333))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(15.dp))

                ) {

                    Image(
                        painter = painterResource(R.drawable.beedrill),
                        contentDescription = "Imagen Beedrill",
                        modifier = Modifier
                            .height(110.dp)
                            .width(120.dp)
                            .background(Color(0xFFC4FFB9))

                            .clickable {

                                coroutineScope.launch {
                                    withContext(Dispatchers.IO) {

                                        val dbHelper = BaseDeDatosHelper(context)
                                        val readableDB = dbHelper.readableDatabase
                                        val cursor = readableDB.rawQuery("SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 2", null) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor = c.getString(0) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo1 = primerValor

                                            }
                                        }

                                        cursor?.close()

                                        val cursor2 = readableDB.rawQuery("SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 0", null) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor2?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor = c.getString(1) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo2 = ", " + primerValor
                                            }
                                        }

                                        cursor2?.close()

                                        val database = Room.databaseBuilder(
                                            context,
                                            TablaRoomDatos::class.java, "debilidades"
                                        ).build()

                                        val dao = database.tablaRoomDao()

                                        val pokemonName = "Beedrill"
                                        val pokemon = dao.getById(15)
                                        println("La debilidad 1 de $pokemonName es: ${pokemon?.debilidad1}")
                                        variableGlobalDebilidad1 = "${pokemon?.debilidad1}, ${pokemon?.debilidad2}, ${pokemon?.debilidad3}, ${pokemon?.debilidad4}"
                                        variableGlobalNombre = pokemonName
                                    }
                                }
                            },
                    )
                }
            }

            item {

                Box(
                    modifier = Modifier.background(Color(0xFF333333))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(15.dp))

                ) {

                    Image(
                        painter = painterResource(R.drawable.pidgey),
                        contentDescription = "Imagen Pidgey",
                        modifier = Modifier
                            .height(110.dp)
                            .width(120.dp)
                            .background(Color(0xFFCCCCCC))

                            .clickable {

                                coroutineScope.launch {
                                    withContext(Dispatchers.IO) {

                                        val dbHelper = BaseDeDatosHelper(context)
                                        val readableDB = dbHelper.readableDatabase
                                        val cursor = readableDB.rawQuery("SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 3", null) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor = c.getString(0) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo1 = primerValor

                                            }
                                        }

                                        cursor?.close()

                                        val cursor2 = readableDB.rawQuery("SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 3", null) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor2?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor = c.getString(1) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo2 = ", " + primerValor
                                            }
                                        }

                                        cursor2?.close()

                                        val database = Room.databaseBuilder(
                                            context,
                                            TablaRoomDatos::class.java, "debilidades"
                                        ).build()

                                        val dao = database.tablaRoomDao()

                                        val pokemonName = "Pidgey"
                                        val pokemon = dao.getById(16)
                                        println("La debilidad 1 de $pokemonName es: ${pokemon?.debilidad1}")
                                        variableGlobalDebilidad1 = "${pokemon?.debilidad1}, ${pokemon?.debilidad2}, ${pokemon?.debilidad3} ${pokemon?.debilidad4}"
                                        variableGlobalNombre = pokemonName
                                    }
                                }
                            },
                    )
                }
            }

            item {

                Box(
                    modifier = Modifier.background(Color(0xFF333333))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(15.dp))

                ) {

                    Image(
                        painter = painterResource(R.drawable.pidgeotto),
                        contentDescription = "Imagen Pidgeotto",
                        modifier = Modifier
                            .height(110.dp)
                            .width(120.dp)
                            .background(Color(0xFFCCCCCC))

                            .clickable {

                                coroutineScope.launch {
                                    withContext(Dispatchers.IO) {

                                        val dbHelper = BaseDeDatosHelper(context)
                                        val readableDB = dbHelper.readableDatabase
                                        val cursor = readableDB.rawQuery("SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 3", null) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor = c.getString(0) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo1 = primerValor

                                            }
                                        }

                                        cursor?.close()

                                        val cursor2 = readableDB.rawQuery("SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 3", null) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor2?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor = c.getString(1) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo2 = ", " + primerValor
                                            }
                                        }

                                        cursor2?.close()

                                        val database = Room.databaseBuilder(
                                            context,
                                            TablaRoomDatos::class.java, "debilidades"
                                        ).build()

                                        val dao = database.tablaRoomDao()

                                        val pokemonName = "Pidgeotto"
                                        val pokemon = dao.getById(17)
                                        println("La debilidad 1 de $pokemonName es: ${pokemon?.debilidad1}")
                                        variableGlobalDebilidad1 = "${pokemon?.debilidad1}, ${pokemon?.debilidad2}, ${pokemon?.debilidad3} ${pokemon?.debilidad4}"
                                        variableGlobalNombre = pokemonName
                                    }
                                }
                            },
                    )
                }
            }

            item {

                Box(
                    modifier = Modifier.background(Color(0xFF333333))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(15.dp))

                ) {

                    Image(
                        painter = painterResource(R.drawable.pidgeot),
                        contentDescription = "Imagen Pidgeot",
                        modifier = Modifier
                            .height(110.dp)
                            .width(120.dp)
                            .background(Color(0xFFCCCCCC))

                            .clickable {

                                coroutineScope.launch {
                                    withContext(Dispatchers.IO) {

                                        val dbHelper = BaseDeDatosHelper(context)
                                        val readableDB = dbHelper.readableDatabase
                                        val cursor = readableDB.rawQuery("SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 3", null) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor = c.getString(0) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo1 = primerValor

                                            }
                                        }

                                        cursor?.close()

                                        val cursor2 = readableDB.rawQuery("SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 3", null) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor2?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor = c.getString(1) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo2 = ", " + primerValor
                                            }
                                        }

                                        cursor2?.close()

                                        val database = Room.databaseBuilder(
                                            context,
                                            TablaRoomDatos::class.java, "debilidades"
                                        ).build()

                                        val dao = database.tablaRoomDao()

                                        val pokemonName = "Pidgeot"
                                        val pokemon = dao.getById(18)
                                        println("La debilidad 1 de $pokemonName es: ${pokemon?.debilidad1}")
                                        variableGlobalDebilidad1 = "${pokemon?.debilidad1}, ${pokemon?.debilidad2}, ${pokemon?.debilidad3} ${pokemon?.debilidad4}"
                                        variableGlobalNombre = pokemonName
                                    }
                                }

                            },
                    )
                }
            }

            item {

                Box(
                    modifier = Modifier.background(Color(0xFF333333))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(15.dp))

                ) {

                    Image(
                        painter = painterResource(R.drawable.rattata),
                        contentDescription = "Imagen Rattata",
                        modifier = Modifier
                            .height(110.dp)
                            .width(120.dp)
                            .background(Color(0xFFCCCCCC))

                            .clickable {

                                coroutineScope.launch {
                                    withContext(Dispatchers.IO) {

                                        val dbHelper = BaseDeDatosHelper(context)
                                        val readableDB = dbHelper.readableDatabase
                                        val cursor = readableDB.rawQuery("SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 3", null) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor = c.getString(0) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo1 = primerValor
                                                variableGlobalTipo2 = ""

                                            }
                                        }

                                        cursor?.close()

                                        val database = Room.databaseBuilder(
                                            context,
                                            TablaRoomDatos::class.java, "debilidades"
                                        ).build()

                                        val dao = database.tablaRoomDao()

                                        val pokemonName = "Rattata"
                                        val pokemon = dao.getById(19)
                                        println("La debilidad 1 de $pokemonName es: ${pokemon?.debilidad1}")
                                        variableGlobalDebilidad1 = "${pokemon?.debilidad1} ${pokemon?.debilidad2} ${pokemon?.debilidad3} ${pokemon?.debilidad4}"
                                        variableGlobalNombre = pokemonName
                                    }
                                }
                            },
                    )
                }
            }

            item {

                Box(
                    modifier = Modifier.background(Color(0xFF333333))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(15.dp))

                ) {

                    Image(
                        painter = painterResource(R.drawable.raticate),
                        contentDescription = "Imagen Raticate",
                        modifier = Modifier
                            .height(110.dp)
                            .width(120.dp)
                            .background(Color(0xFFCCCCCC))

                            .clickable {

                                coroutineScope.launch {
                                    withContext(Dispatchers.IO) {

                                        val dbHelper = BaseDeDatosHelper(context)
                                        val readableDB = dbHelper.readableDatabase
                                        val cursor = readableDB.rawQuery("SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 3", null) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor = c.getString(0) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo1 = primerValor
                                                variableGlobalTipo2 = ""

                                            }
                                        }

                                        cursor?.close()

                                        val database = Room.databaseBuilder(
                                            context,
                                            TablaRoomDatos::class.java, "debilidades"
                                        ).build()

                                        val dao = database.tablaRoomDao()

                                        val pokemonName = "Raticate"
                                        val pokemon = dao.getById(20)
                                        println("La debilidad 1 de $pokemonName es: ${pokemon?.debilidad1}")
                                        variableGlobalDebilidad1 = "${pokemon?.debilidad1} ${pokemon?.debilidad2} ${pokemon?.debilidad3} ${pokemon?.debilidad4}"
                                        variableGlobalNombre = pokemonName
                                    }
                                }
                            },
                    )
                }
            }

            item {

                Box(
                    modifier = Modifier.background(Color(0xFF333333))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(15.dp))

                ) {

                    Image(
                        painter = painterResource(R.drawable.spearrow),
                        contentDescription = "Imagen Spearrow",
                        modifier = Modifier
                            .height(110.dp)
                            .width(120.dp)
                            .background(Color(0xFFCCCCCC))

                            .clickable {

                                coroutineScope.launch {
                                    withContext(Dispatchers.IO) {

                                        val dbHelper = BaseDeDatosHelper(context)
                                        val readableDB = dbHelper.readableDatabase
                                        val cursor = readableDB.rawQuery("SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 3", null) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor = c.getString(0) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo1 = primerValor

                                            }
                                        }

                                        cursor?.close()

                                        val cursor2 = readableDB.rawQuery("SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 3", null) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor2?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor = c.getString(1) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo2 = ", " + primerValor
                                            }
                                        }

                                        cursor2?.close()

                                        val database = Room.databaseBuilder(
                                            context,
                                            TablaRoomDatos::class.java, "debilidades"
                                        ).build()

                                        val dao = database.tablaRoomDao()

                                        val pokemonName = "Spearrow"
                                        val pokemon = dao.getById(21)
                                        println("La debilidad 1 de $pokemonName es: ${pokemon?.debilidad1}")
                                        variableGlobalDebilidad1 = "${pokemon?.debilidad1}, ${pokemon?.debilidad2}, ${pokemon?.debilidad3} ${pokemon?.debilidad4}"
                                        variableGlobalNombre = pokemonName
                                    }
                                }
                            },
                    )
                }
            }

            item {

                Box(
                    modifier = Modifier.background(Color(0xFF333333))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(15.dp))

                ) {

                    Image(
                        painter = painterResource(R.drawable.fearrow),
                        contentDescription = "Imagen Fearrow",
                        modifier = Modifier
                            .height(110.dp)
                            .width(120.dp)
                            .background(Color(0xFFCCCCCC))

                            .clickable {

                                coroutineScope.launch {
                                    withContext(Dispatchers.IO) {

                                        val dbHelper = BaseDeDatosHelper(context)
                                        val readableDB = dbHelper.readableDatabase
                                        val cursor = readableDB.rawQuery("SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 3", null) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor = c.getString(0) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo1 = primerValor

                                            }
                                        }

                                        cursor?.close()

                                        val cursor2 = readableDB.rawQuery("SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 3", null) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor2?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor = c.getString(1) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo2 = ", " + primerValor
                                            }
                                        }

                                        cursor2?.close()

                                        val database = Room.databaseBuilder(
                                            context,
                                            TablaRoomDatos::class.java, "debilidades"
                                        ).build()

                                        val dao = database.tablaRoomDao()

                                        val pokemonName = "Fearow"
                                        val pokemon = dao.getById(22)
                                        println("La debilidad 1 de $pokemonName es: ${pokemon?.debilidad1}")
                                        variableGlobalDebilidad1 = "${pokemon?.debilidad1}, ${pokemon?.debilidad2}, ${pokemon?.debilidad3} ${pokemon?.debilidad4}"
                                        variableGlobalNombre = pokemonName
                                    }
                                }
                            },
                    )
                }
            }

            item {

                Box(
                    modifier = Modifier.background(Color(0xFF333333))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(15.dp))

                ) {

                    Image(
                        painter = painterResource(R.drawable.ekans),
                        contentDescription = "Imagen Ekans",
                        modifier = Modifier
                            .height(110.dp)
                            .width(120.dp)
                            .background(Color(0xFFD8BFD8))

                            .clickable {

                                coroutineScope.launch {
                                    withContext(Dispatchers.IO) {

                                        val dbHelper = BaseDeDatosHelper(context)
                                        val readableDB = dbHelper.readableDatabase
                                        val cursor = readableDB.rawQuery("SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 0", null) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor = c.getString(1) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo1 = primerValor
                                                variableGlobalTipo2 = ""

                                            }
                                        }

                                        cursor?.close()

                                        val database = Room.databaseBuilder(
                                            context,
                                            TablaRoomDatos::class.java, "debilidades"
                                        ).build()

                                        val dao = database.tablaRoomDao()

                                        val pokemonName = "Ekans"
                                        val pokemon = dao.getById(23)
                                        println("La debilidad 1 de $pokemonName es: ${pokemon?.debilidad1}")
                                        variableGlobalDebilidad1 = "${pokemon?.debilidad1}, ${pokemon?.debilidad2} ${pokemon?.debilidad3} ${pokemon?.debilidad4}"
                                        variableGlobalNombre = pokemonName
                                    }
                                }
                            },
                    )
                }
            }

            item {

                Box(
                    modifier = Modifier.background(Color(0xFF333333))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(15.dp))

                ) {

                    Image(
                        painter = painterResource(R.drawable.arbok),
                        contentDescription = "Imagen Arbok",
                        modifier = Modifier
                            .height(110.dp)
                            .width(120.dp)
                            .background(Color(0xFFD8BFD8))

                            .clickable {

                                coroutineScope.launch {
                                    withContext(Dispatchers.IO) {

                                        val dbHelper = BaseDeDatosHelper(context)
                                        val readableDB = dbHelper.readableDatabase
                                        val cursor = readableDB.rawQuery("SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 0", null) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor = c.getString(1) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo1 = primerValor
                                                variableGlobalTipo2 = ""

                                            }
                                        }

                                        cursor?.close()

                                        val database = Room.databaseBuilder(
                                            context,
                                            TablaRoomDatos::class.java, "debilidades"
                                        ).build()

                                        val dao = database.tablaRoomDao()

                                        val pokemonName = "Arbok"
                                        val pokemon = dao.getById(24)
                                        println("La debilidad 1 de $pokemonName es: ${pokemon?.debilidad1}")
                                        variableGlobalDebilidad1 = "${pokemon?.debilidad1}, ${pokemon?.debilidad2} ${pokemon?.debilidad3} ${pokemon?.debilidad4}"
                                        variableGlobalNombre = pokemonName
                                    }
                                }
                            },
                    )
                }
            }

            item {

                Box(
                    modifier = Modifier.background(Color(0xFF333333))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(15.dp))

                ) {

                    Image(
                        painter = painterResource(R.drawable.pikachu),
                        contentDescription = "Imagen Pikachu",
                        modifier = Modifier
                            .height(110.dp)
                            .width(120.dp)
                            .background(Color(0xFFFFFF99))

                            .clickable {

                                coroutineScope.launch {
                                    withContext(Dispatchers.IO) {

                                        val dbHelper = BaseDeDatosHelper(context)
                                        val readableDB = dbHelper.readableDatabase
                                        val cursor = readableDB.rawQuery("SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 2", null) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor = c.getString(0) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo1 = primerValor
                                                variableGlobalTipo2 = ""

                                            }
                                        }

                                        cursor?.close()

                                        val database = Room.databaseBuilder(
                                            context,
                                            TablaRoomDatos::class.java, "debilidades"
                                        ).build()

                                        val dao = database.tablaRoomDao()

                                        val pokemonName = "Pikachu"
                                        val pokemon = dao.getById(25)
                                        println("La debilidad 1 de $pokemonName es: ${pokemon?.debilidad1}")
                                        variableGlobalDebilidad1 = "${pokemon?.debilidad1} ${pokemon?.debilidad2} ${pokemon?.debilidad3} ${pokemon?.debilidad4}"
                                        variableGlobalNombre = pokemonName
                                    }
                                }
                            },
                    )
                }
            }

            item {

                Box(
                    modifier = Modifier.background(Color(0xFF333333))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(15.dp))

                ) {

                    Image(
                        painter = painterResource(R.drawable.raichu),
                        contentDescription = "Imagen Raichu",
                        modifier = Modifier
                            .height(110.dp)
                            .width(120.dp)
                            .background(Color(0xFFFFFF99))

                            .clickable {

                                coroutineScope.launch {
                                    withContext(Dispatchers.IO) {

                                        val dbHelper = BaseDeDatosHelper(context)
                                        val readableDB = dbHelper.readableDatabase
                                        val cursor = readableDB.rawQuery("SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 2", null) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor = c.getString(0) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo1 = primerValor
                                                variableGlobalTipo2 = ""

                                            }
                                        }

                                        cursor?.close()

                                        val database = Room.databaseBuilder(
                                            context,
                                            TablaRoomDatos::class.java, "debilidades"
                                        ).build()

                                        val dao = database.tablaRoomDao()

                                        val pokemonName = "Raichu"
                                        val pokemon = dao.getById(26)
                                        println("La debilidad 1 de $pokemonName es: ${pokemon?.debilidad1}")
                                        variableGlobalDebilidad1 = "${pokemon?.debilidad1} ${pokemon?.debilidad2} ${pokemon?.debilidad3} ${pokemon?.debilidad4}"
                                        variableGlobalNombre = pokemonName
                                    }
                                }
                            },
                    )
                }
            }

            item {

                Box(
                    modifier = Modifier.background(Color(0xFF333333))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(15.dp))

                ) {

                    Image(
                        painter = painterResource(R.drawable.sandshrew),
                        contentDescription = "Imagen Sandshrew",
                        modifier = Modifier
                            .height(110.dp)
                            .width(120.dp)
                            .background(Color(0xFFD2B48C))

                            .clickable {

                                coroutineScope.launch {
                                    withContext(Dispatchers.IO) {

                                        val dbHelper = BaseDeDatosHelper(context)
                                        val readableDB = dbHelper.readableDatabase
                                        val cursor = readableDB.rawQuery(
                                            "SELECT * FROM ${BaseDeDatos.TABLA_NOMBRE} LIMIT 1 OFFSET 4",
                                            null
                                        ) // Se cambia el OFFSET 1 por otro numero para filas

                                        cursor?.use { c ->
                                            if (c.moveToFirst()) {
                                                val primerValor =
                                                    c.getString(0) // se cambia el 1 por otro numero para cambiar columna
                                                // Guarda el valor de la segunda columna de la segunda fila en globalVariable
                                                variableGlobalTipo1 = primerValor
                                                variableGlobalTipo2 = ""

                                            }
                                        }

                                        cursor?.close()

                                        val database = Room.databaseBuilder(
                                            context,
                                            TablaRoomDatos::class.java, "debilidades"
                                        ).build()

                                        val dao = database.tablaRoomDao()

                                        val pokemonName = "Sandshrew"
                                        val pokemon = dao.getById(27)
                                        println("La debilidad 1 de $pokemonName es: ${pokemon?.debilidad1}")
                                        variableGlobalDebilidad1 = "${pokemon?.debilidad1} ${pokemon?.debilidad2} ${pokemon?.debilidad3} ${pokemon?.debilidad4}"
                                        variableGlobalNombre = pokemonName

                                    }
                                }

                            }
                    )
                }
            }

            item {

                Box(
                    modifier = Modifier.background(Color(0xFF333333))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(15.dp))

                ) {

                    Image(
                        painter = painterResource(R.drawable.colorfondo),
                        contentDescription = "Imagen colorfondo",
                        modifier = Modifier
                            .height(130.dp)
                            .width(120.dp)
                            .background(Color(0xFF333333))

                            .clickable {


                            },
                    )
                }
            }

            item {

                Box(
                    modifier = Modifier.background(Color(0xFF333333))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(15.dp))

                ) {

                    Image(
                        painter = painterResource(R.drawable.colorfondo),
                        contentDescription = "Imagen colorfondo",
                        modifier = Modifier
                            .height(130.dp)
                            .width(140.dp)
                            .background(Color(0xFF333333))

                            .clickable {


                            },
                    )
                }
            }

            item {

                Box(
                    modifier = Modifier.background(Color(0xFF333333))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(15.dp))

                ) {

                    Image(
                        painter = painterResource(R.drawable.colorfondo),
                        contentDescription = "Imagen colorfondo",
                        modifier = Modifier
                            .height(130.dp)
                            .width(140.dp)
                            .background(Color(0xFF333333))

                            .clickable {


                            }
                    )
                }
            }

        }
    }
}

// El Preview esta para que se vea que se pasan los datos del FirstScreenViewModel aqui

@Preview
@Composable
fun imagenPrueba(viewModel: FirstScreenViewModel = hiltViewModel()) {
    val state = viewModel.state
    val firstEnlace = state.firstOrNull()
    hiltPrueba(state)

}

@Composable
fun hiltPrueba(state: List<Enlace> = emptyList()) {
    LazyColumn {
        items(state) { enlace ->

            Enlace(enlace)

        }
    }

}

@Composable
fun Enlace(enlace: Enlace) {
    Row {
        Text(enlace.name)

    }

}

