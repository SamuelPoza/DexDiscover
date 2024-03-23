package com.example.myapplication.screen.navigation

// Creo las rutas de las Screens

sealed class AppScreens(val route: String){
    object FirstScreen: AppScreens(route = "first_screen")
    object SecondScreen: AppScreens(route = "second_screen")
}