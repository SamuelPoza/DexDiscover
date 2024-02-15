package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.screens.FirstScreen
import com.example.myapplication.screens.SecondScreen

@Composable
fun AppNavigation () {
    val navControler = rememberNavController()
    NavHost(navController = navControler, startDestination = AppScreens.FirstScreen.route) {
        composable(route  = AppScreens.FirstScreen.route) {
            FirstScreen(navControler)
        }
        composable(route  = AppScreens.SecondScreen.route) {
            SecondScreen(navControler)
        }
    }
}