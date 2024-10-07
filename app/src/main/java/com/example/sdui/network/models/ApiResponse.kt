package com.example.sdui.network.models

// ApiResponse.kt
data class ApiResponse(
    val acao: String,
    val componentes: List<ComponentData>
)

data class ComponentData(
    val tipo: String,
    val parametros: Map<String, Any> // Usamos um Map para deserializar parâmetros flexíveis
)