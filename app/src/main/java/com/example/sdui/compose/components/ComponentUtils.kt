package com.example.sdui.compose.components

import androidx.navigation.NavController
import com.example.sdui.compose.navigation.NavigationAction

fun handleNavigation(action: NavigationAction, navController: NavController, actionParameters: String? = null) {
    when (action) {
        NavigationAction.EXIBIR_HOME -> navController.navigate("home")
        NavigationAction.EXIBIR_TELA_SDUI -> navController.navigate("serverDriven/${actionParameters}")
    }
}
