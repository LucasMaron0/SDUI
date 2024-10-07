package com.example.sdui.compose.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sdui.compose.navigation.NavigationAction


@Composable
fun RenderText(component: UiComponent.TextComponent) {
    Text(
        text = component.text,
        style = component.style ?: TextStyle.Default,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    )
}

@Composable
fun RenderButton(component: UiComponent.ButtonComponent, navController: NavController) {
    Button(
        onClick = {
           handleNavigation(component.onClickAction, navController, component.actionParameters)
        },
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(text = component.text)
    }
}

fun handleNavigation(action: NavigationAction, navController: NavController, actionParameters: String? = null) {
    when (action) {
        NavigationAction.EXIBIR_HOME -> navController.navigate("home")
        NavigationAction.EXIBIR_TELA_SDUI -> navController.navigate("serverDriven/${actionParameters}")
    }
}
