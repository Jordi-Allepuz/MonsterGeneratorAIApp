package com.example.monstergeneratoraiapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.monstergeneratoraiapp.ui.screens.GeneratorScreen
import com.example.monstergeneratoraiapp.ui.screens.SplashScreen

@Composable
fun AppNavigation() {

    val navigationController = rememberNavController()
    NavHost(
        navController = navigationController,
        startDestination = AppScreens.SplashScreen.route
    ) {
        composable(AppScreens.SplashScreen.route) {
            SplashScreen(navigationController)
        }
//        composable(AppScreens.LoginScreen.route) {
//            LoginScreen()
//        }
//        composable(AppScreens.SignUpScreen.route) {
//            SignUpScreen()
//        }
        composable(AppScreens.GeneratorScreen.route) {
            GeneratorScreen()
        }
//        composable(AppScreens.ColectionScreen.route) {
//            ColectionScreen()
//        }
    }


}