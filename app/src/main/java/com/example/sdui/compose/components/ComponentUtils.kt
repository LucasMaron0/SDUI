package com.example.sdui.compose.components

import androidx.navigation.NavController
import com.example.sdui.compose.navigation.NavigationAction
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

fun handleNavigation(action: NavigationAction, navController: NavController, actionParameters: Any? = null) {
    val jsonParams = actionParameters?.let {
        val serializedParams = Gson().toJson(it)
        URLEncoder.encode(serializedParams, StandardCharsets.UTF_8.toString())
    }

    when (action) {
        NavigationAction.SHOW_HOME_SCREEN -> navController.navigate("home")
        NavigationAction.SHOW_SDUI_SCREEN -> navController.navigate("serverDriven/$jsonParams")
    }
}
