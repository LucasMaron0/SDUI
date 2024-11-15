package com.example.sdui.network.models

import com.example.sdui.compose.components.UiComponent
import com.example.sdui.compose.navigation.NavigationAction

data class MappedResponse(
    val acao: NavigationAction,
    val parametros: List<UiComponent>
)