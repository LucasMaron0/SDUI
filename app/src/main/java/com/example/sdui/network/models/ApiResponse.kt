package com.example.sdui.network.models

data class ApiResponse(
    val acao: String,
    val parametros: List<ComponentData>
)

data class ComponentData(
    val tipo: String,
    val parametros: Map<String, Any>
)
