package com.example.myapplication.screen.first

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.myapplication.R
import com.example.myapplication.screen.navigation.AppScreens
import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject


// Creo variables globales

var variableGlobalTipo1 by mutableStateOf("")
var variableGlobalTipo2 by mutableStateOf("")
var variableGlobalDebilidades by mutableStateOf("")
var variableGlobalNombre by mutableStateOf("")


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "Range")
@Composable
fun FirstScreen(navController: NavController) {

    val viewModel: FirstScreenViewModel = viewModel() // Obtiene una instancia del ViewModel

    MyScaffoldFirstScreen(navController, viewModel)

}

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
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "Range", "SuspiciousIndentation")
@Composable
fun MyScaffoldFirstScreen(navController: NavController, viewModel: FirstScreenViewModel) {

    val context = LocalContext.current

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
                            text = stringResource(id = R.string.nombre) + " " + variableGlobalNombre,
                            fontSize = 15.sp,
                            color = Color.White,
                            modifier = Modifier
                                .background(Color(0xFF1A1A1A))
                                .padding(8.dp),
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif
                        )

                        Text(
                            text = stringResource(id = R.string.tipos) + " " + variableGlobalTipo1 + ", " + variableGlobalTipo2,
                            fontSize = 15.sp,
                            color = Color.White,
                            modifier = Modifier
                                .background(Color(0xFF1A1A1A))
                                .padding(8.dp),
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif
                        )

                        Text(
                            text = stringResource(id = R.string.debilidades) + " " + variableGlobalDebilidades,
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

        }
    ) {

        // Lista con todas las imágenes
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

                                viewModel.consultarTipo1(context, 0, 0) { tipo1 -> variableGlobalTipo1 = tipo1
                                    viewModel.consultarTipo2(context, 0, 1) { tipo2 -> variableGlobalTipo2 = tipo2
                                        viewModel.consultarDebilidades(context, "Bulbasaur") { debilidades -> variableGlobalDebilidades = debilidades
                                            viewModel.consultarNombre(context, "Bulbasaur") { nombre -> variableGlobalNombre = nombre

                                            }
                                        }
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

                                viewModel.consultarTipo1(context, 0, 0) { tipo1 -> variableGlobalTipo1 = tipo1
                                    viewModel.consultarTipo2(context, 0, 1) { tipo2 -> variableGlobalTipo2 = tipo2
                                        viewModel.consultarDebilidades(context, "Ivysaur") { debilidades -> variableGlobalDebilidades = debilidades
                                            viewModel.consultarNombre(context, "Ivysaur") { nombre -> variableGlobalNombre = nombre

                                            }
                                        }
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

                                viewModel.consultarTipo1(context, 0, 0) { tipo1 -> variableGlobalTipo1 = tipo1
                                    viewModel.consultarTipo2(context, 0, 1) { tipo2 -> variableGlobalTipo2 = tipo2
                                        viewModel.consultarDebilidades(context, "Venosaur") { debilidades -> variableGlobalDebilidades = debilidades
                                            viewModel.consultarNombre(context, "Venosaur") { nombre -> variableGlobalNombre = nombre

                                            }
                                        }
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

                                viewModel.consultarTipo1(context, 1, 0) { tipo1 -> variableGlobalTipo1 = tipo1
                                    variableGlobalTipo2 = ""
                                        viewModel.consultarDebilidades(context, "Charmander") { debilidades -> variableGlobalDebilidades = debilidades
                                            viewModel.consultarNombre(context, "Charmander") { nombre -> variableGlobalNombre = nombre


                                        }
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

                                viewModel.consultarTipo1(context, 1, 0) { tipo1 -> variableGlobalTipo1 = tipo1
                                    variableGlobalTipo2 = ""
                                        viewModel.consultarDebilidades(context, "Charmeleon") { debilidades -> variableGlobalDebilidades = debilidades
                                            viewModel.consultarNombre(context, "Charmeleon") { nombre -> variableGlobalNombre = nombre


                                        }
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

                                viewModel.consultarTipo1(context, 1, 0) { tipo1 -> variableGlobalTipo1 = tipo1
                                    viewModel.consultarTipo2(context, 3, 1) { tipo2 -> variableGlobalTipo2 = tipo2
                                        viewModel.consultarDebilidades(context, "Charizard") { debilidades -> variableGlobalDebilidades = debilidades
                                            viewModel.consultarNombre(context, "Charizard") { nombre -> variableGlobalNombre = nombre

                                            }
                                        }
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

                                viewModel.consultarTipo1(context, 1, 1) { tipo1 -> variableGlobalTipo1 = tipo1
                                    variableGlobalTipo2 = ""
                                        viewModel.consultarDebilidades(context, "Squirtle") { debilidades -> variableGlobalDebilidades = debilidades
                                            viewModel.consultarNombre(context, "Squirtle") { nombre -> variableGlobalNombre = nombre


                                        }
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

                                viewModel.consultarTipo1(context, 1, 1) { tipo1 -> variableGlobalTipo1 = tipo1
                                    variableGlobalTipo2 = ""
                                        viewModel.consultarDebilidades(context, "Wartortle") { debilidades -> variableGlobalDebilidades = debilidades
                                            viewModel.consultarNombre(context, "Wartortle") { nombre -> variableGlobalNombre = nombre


                                        }
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

                                viewModel.consultarTipo1(context, 1, 1) { tipo1 -> variableGlobalTipo1 = tipo1
                                    variableGlobalTipo2 = ""
                                        viewModel.consultarDebilidades(context, "Blastoise") { debilidades -> variableGlobalDebilidades = debilidades
                                            viewModel.consultarNombre(context, "Blastoise") { nombre -> variableGlobalNombre = nombre


                                        }
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

                                viewModel.consultarTipo1(context, 2, 1) { tipo1 -> variableGlobalTipo1 = tipo1
                                    variableGlobalTipo2 = ""
                                        viewModel.consultarDebilidades(context, "Caterpie") { debilidades -> variableGlobalDebilidades = debilidades
                                            viewModel.consultarNombre(context, "Caterpie") { nombre -> variableGlobalNombre = nombre


                                        }
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

                                viewModel.consultarTipo1(context, 2, 1) { tipo1 -> variableGlobalTipo1 = tipo1
                                    variableGlobalTipo2 = ""
                                        viewModel.consultarDebilidades(context, "Metapod") { debilidades -> variableGlobalDebilidades = debilidades
                                            viewModel.consultarNombre(context, "Metapod") { nombre -> variableGlobalNombre = nombre


                                        }
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

                                viewModel.consultarTipo1(context, 2, 1) { tipo1 -> variableGlobalTipo1 = tipo1
                                    viewModel.consultarTipo2(context, 3, 1) { tipo2 -> variableGlobalTipo2 = tipo2
                                        viewModel.consultarDebilidades(context, "Butterfree") { debilidades -> variableGlobalDebilidades = debilidades
                                            viewModel.consultarNombre(context, "Butterfree") { nombre -> variableGlobalNombre = nombre

                                            }
                                        }
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

                                viewModel.consultarTipo1(context, 2, 1) { tipo1 -> variableGlobalTipo1 = tipo1
                                    viewModel.consultarTipo2(context, 0, 1) { tipo2 -> variableGlobalTipo2 = tipo2
                                        viewModel.consultarDebilidades(context, "Weedle") { debilidades -> variableGlobalDebilidades = debilidades
                                            viewModel.consultarNombre(context, "Weedle") { nombre -> variableGlobalNombre = nombre

                                            }
                                        }
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

                                viewModel.consultarTipo1(context, 2, 1) { tipo1 -> variableGlobalTipo1 = tipo1
                                    viewModel.consultarTipo2(context, 0, 1) { tipo2 -> variableGlobalTipo2 = tipo2
                                        viewModel.consultarDebilidades(context, "Kakuna") { debilidades -> variableGlobalDebilidades = debilidades
                                            viewModel.consultarNombre(context, "Kakuna") { nombre -> variableGlobalNombre = nombre

                                            }
                                        }
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

                                viewModel.consultarTipo1(context, 2, 1) { tipo1 -> variableGlobalTipo1 = tipo1
                                    viewModel.consultarTipo2(context, 0, 1) { tipo2 -> variableGlobalTipo2 = tipo2
                                        viewModel.consultarDebilidades(context, "Beedrill") { debilidades -> variableGlobalDebilidades = debilidades
                                            viewModel.consultarNombre(context, "Beedrill") { nombre -> variableGlobalNombre = nombre

                                            }
                                        }
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

                                viewModel.consultarTipo1(context, 3, 0) { tipo1 -> variableGlobalTipo1 = tipo1
                                    viewModel.consultarTipo2(context, 3, 1) { tipo2 -> variableGlobalTipo2 = tipo2
                                        viewModel.consultarDebilidades(context, "Pidgey") { debilidades -> variableGlobalDebilidades = debilidades
                                            viewModel.consultarNombre(context, "Pidgey") { nombre -> variableGlobalNombre = nombre

                                            }
                                        }
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

                                viewModel.consultarTipo1(context, 3, 0) { tipo1 -> variableGlobalTipo1 = tipo1
                                    viewModel.consultarTipo2(context, 3, 1) { tipo2 -> variableGlobalTipo2 = tipo2
                                        viewModel.consultarDebilidades(context, "Pidgeotto") { debilidades -> variableGlobalDebilidades = debilidades
                                            viewModel.consultarNombre(context, "Pidgeotto") { nombre -> variableGlobalNombre = nombre

                                            }
                                        }
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

                                viewModel.consultarTipo1(context, 3, 0) { tipo1 -> variableGlobalTipo1 = tipo1
                                    viewModel.consultarTipo2(context, 3, 1) { tipo2 -> variableGlobalTipo2 = tipo2
                                        viewModel.consultarDebilidades(context, "Pidgeot") { debilidades -> variableGlobalDebilidades = debilidades
                                            viewModel.consultarNombre(context, "Pidgeot") { nombre -> variableGlobalNombre = nombre

                                            }
                                        }
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

                                viewModel.consultarTipo1(context, 3, 0) { tipo1 -> variableGlobalTipo1 = tipo1
                                    variableGlobalTipo2 = ""
                                        viewModel.consultarDebilidades(context, "Rattata") { debilidades -> variableGlobalDebilidades = debilidades
                                            viewModel.consultarNombre(context, "Rattata") { nombre -> variableGlobalNombre = nombre


                                        }
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

                                viewModel.consultarTipo1(context, 3, 0) { tipo1 -> variableGlobalTipo1 = tipo1
                                    variableGlobalTipo2 = ""
                                        viewModel.consultarDebilidades(context, "Raticate") { debilidades -> variableGlobalDebilidades = debilidades
                                            viewModel.consultarNombre(context, "Raticate") { nombre -> variableGlobalNombre = nombre


                                        }
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

                                viewModel.consultarTipo1(context, 3, 0) { tipo1 -> variableGlobalTipo1 = tipo1
                                    viewModel.consultarTipo2(context, 3, 1) { tipo2 -> variableGlobalTipo2 = tipo2
                                        viewModel.consultarDebilidades(context, "Spearrow") { debilidades -> variableGlobalDebilidades = debilidades
                                            viewModel.consultarNombre(context, "Spearrow") { nombre -> variableGlobalNombre = nombre

                                            }
                                        }
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
                        contentDescription = "Imagen Fearow",
                        modifier = Modifier
                            .height(110.dp)
                            .width(120.dp)
                            .background(Color(0xFFCCCCCC))

                            .clickable {

                                viewModel.consultarTipo1(context, 3, 0) { tipo1 -> variableGlobalTipo1 = tipo1
                                    viewModel.consultarTipo2(context, 3, 1) { tipo2 -> variableGlobalTipo2 = tipo2
                                        viewModel.consultarDebilidades(context, "Fearow") { debilidades -> variableGlobalDebilidades = debilidades
                                            viewModel.consultarNombre(context, "Fearow") { nombre -> variableGlobalNombre = nombre

                                            }
                                        }
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

                                viewModel.consultarTipo1(context, 0, 1) { tipo1 -> variableGlobalTipo1 = tipo1
                                    variableGlobalTipo2 = ""
                                        viewModel.consultarDebilidades(context, "Ekans") { debilidades -> variableGlobalDebilidades = debilidades
                                            viewModel.consultarNombre(context, "Ekans") { nombre -> variableGlobalNombre = nombre


                                        }
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

                                viewModel.consultarTipo1(context, 0, 1) { tipo1 -> variableGlobalTipo1 = tipo1
                                    variableGlobalTipo2 = ""
                                        viewModel.consultarDebilidades(context, "Arbok") { debilidades -> variableGlobalDebilidades = debilidades
                                            viewModel.consultarNombre(context, "Arbok") { nombre -> variableGlobalNombre = nombre


                                        }
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

                                viewModel.consultarTipo1(context, 2, 0) { tipo1 -> variableGlobalTipo1 = tipo1
                                    variableGlobalTipo2 = ""
                                        viewModel.consultarDebilidades(context, "Pikachu") { debilidades -> variableGlobalDebilidades = debilidades
                                            viewModel.consultarNombre(context, "Pikachu") { nombre -> variableGlobalNombre = nombre


                                        }
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

                                viewModel.consultarTipo1(context, 2, 0) { tipo1 -> variableGlobalTipo1 = tipo1
                                    variableGlobalTipo2 = ""
                                        viewModel.consultarDebilidades(context, "Raichu") { debilidades -> variableGlobalDebilidades = debilidades
                                            viewModel.consultarNombre(context, "Raichu") { nombre -> variableGlobalNombre = nombre


                                        }
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

                                viewModel.consultarTipo1(context, 4, 0) { tipo1 -> variableGlobalTipo1 = tipo1
                                    variableGlobalTipo2 = ""
                                        viewModel.consultarDebilidades(context, "Sandshrew") { debilidades -> variableGlobalDebilidades = debilidades
                                            viewModel.consultarNombre(context, "Sandshrew") { nombre -> variableGlobalNombre = nombre


                                        }
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

