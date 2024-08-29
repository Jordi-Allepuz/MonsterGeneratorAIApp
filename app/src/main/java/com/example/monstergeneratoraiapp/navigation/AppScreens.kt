package com.example.monstergeneratoraiapp.navigation

sealed class AppScreens(val route: String) {
    object SplashScreen : AppScreens ("splash")
    object LoginScreen : AppScreens("loginscreen")
    object SignUpScreen : AppScreens ("signupscreen")
    object GeneratorScreen : AppScreens ("generatorscreen")
    object ColectionScreen : AppScreens ("userinfoscreen")

}
