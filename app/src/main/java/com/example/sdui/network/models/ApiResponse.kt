package com.example.sdui.network.models

data class ApiResponse(
    val action: String,
    val params: List<ComponentData>
)

data class ComponentData(
    val type: String,
    val params: Map<String, Any>
)
