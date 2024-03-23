package com.example.myapplication.screen.second

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.screen.navigation.AppScreens



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(navController: NavController) {

       MyScaffoldSecondScreen(navController)

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScaffoldSecondScreen (navController: NavController) {


    Scaffold (topBar = {
        TopAppBar(modifier = Modifier,

            title = {

    Row {
        Image(
            painter = painterResource(R.drawable.flechablanca),
            contentDescription = "Icono busqueda",
            modifier = Modifier
                .height(30.dp)
                .width(30.dp)
                .padding(4.dp)
                .clickable {
                    navController.navigate(route = AppScreens.FirstScreen.route)
                },
        )

        Spacer(modifier = Modifier.width(20.dp))

        Text(text = "Ajustes",
            fontSize = 20.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif)

        }
        },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF1A1A1A))
        )
    }

    )
    {LazyColumn(
        modifier = Modifier
            .background(Color(0xFF333333))
            .fillMaxSize()
            .padding(16.dp)
    ) {
        
        // Aqui lo mismo que en la firstScreen, pongo esto pero eventualmente seran ajustes

        items(1) { index ->

            Spacer(modifier = Modifier.height(65.dp)) // Espacio entre las dos columnas

            Text(text = "Ajustes",
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(25.dp)) // Espacio entre las dos columnas

            Text(text = "Ajustes",
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(25.dp)) // Espacio entre las dos columnas

            Text(text = "Ajustes",
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(25.dp)) // Espacio entre las dos columnas

            Text(text = "Ajustes",
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(25.dp)) // Espacio entre las dos columnas

            Text(text = "Ajustes",
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif,
                color = Color.White
            )

        }
    }

    }

}
