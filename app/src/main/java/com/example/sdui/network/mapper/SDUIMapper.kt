package com.example.sdui.network.mapper

import com.example.sdui.compose.components.UiComponent
import com.example.sdui.compose.navigation.NavigationAction
import com.example.sdui.network.models.ApiResponse
import com.example.sdui.network.models.MappedResponse

fun mapJson(json: ApiResponse): MappedResponse {
    return mapComponentes(json)
}

fun mapComponentes(apiResponse: ApiResponse): MappedResponse {
    val mappedComponents = apiResponse.params.flatMap { componentData ->
        if (componentData.type == "list") {
            val itensLista = componentData.params["listItens"] as? List<Map<String, Any>> ?: emptyList()
            itensLista.mapNotNull { item ->
                val type = item["type"] as? String ?: return@mapNotNull null
                val params = item["params"] as? Map<String, Any> ?: return@mapNotNull null
                mapSingleComponent(type, params)
            }
        } else {
            listOfNotNull(mapSingleComponent(componentData.type, componentData.params))
        }
    }
    return MappedResponse(NavigationAction.valueOf(apiResponse.action), mappedComponents)
}

fun mapNavigationAction(actionString: String?): NavigationAction {
    return when (actionString) {
        "SHOW_HOME_SCREEN" -> NavigationAction.SHOW_HOME_SCREEN
        "SHOW_SDUI_SCREEN" -> NavigationAction.SHOW_SDUI_SCREEN
        else -> NavigationAction.SHOW_HOME_SCREEN
    }
}
fun mapSingleComponent(tipo: String, parametros: Map<String, Any>): UiComponent? {
    return when (tipo) {
        "text" -> mapTextComponent(parametros)
        "button" -> mapButtonComponent(parametros)
        "list" -> mapListaComponent(parametros["listItens"] as? List<Map<String, Any>> ?: emptyList())
        else -> null
    }
}

