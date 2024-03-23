package com.example.myapplication.screen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.screen.first.FirstScreen
import com.example.myapplication.screen.second.SecondScreen

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