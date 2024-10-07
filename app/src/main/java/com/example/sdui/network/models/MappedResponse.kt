package com.example.sdui.network.models

import com.example.sdui.compose.components.UiComponent

data class MappedResponse(
    val acao: String,
    val componentes: List<UiComponent>
)