package com.example.sdui.network.mapper

import com.example.sdui.compose.components.UiComponent
import com.example.sdui.compose.navigation.NavigationAction
import com.example.sdui.network.models.ApiResponse
import com.example.sdui.network.models.MappedResponse
import com.google.gson.Gson

fun mapJson(json: String): MappedResponse {
    val apiResponse: ApiResponse = Gson().fromJson(json, ApiResponse::class.java)
    return mapComponentes(apiResponse)
}

fun mapComponentes(apiResponse: ApiResponse): MappedResponse {
    val mappedComponents = apiResponse.parametros.flatMap { componentData ->
        if (componentData.tipo == "lista") {
            val itensLista = componentData.parametros["itensLista"] as? List<Map<String, Any>> ?: emptyList()
            itensLista.mapNotNull { item ->
                val tipo = item["tipo"] as? String ?: return@mapNotNull null
                val parametros = item["parametros"] as? Map<String, Any> ?: return@mapNotNull null
                mapSingleComponent(tipo, parametros)
            }
        } else {
            listOfNotNull(mapSingleComponent(componentData.tipo, componentData.parametros))
        }
    }
    return MappedResponse(NavigationAction.valueOf(apiResponse.acao), mappedComponents)
}

fun mapNavigationAction(actionString: String?): NavigationAction {
    return when (actionString) {
        "EXIBIR_HOME" -> NavigationAction.EXIBIR_HOME
        "EXIBIR_TELA_SDUI" -> NavigationAction.EXIBIR_TELA_SDUI
        else -> NavigationAction.EXIBIR_HOME
    }
}
fun mapSingleComponent(tipo: String, parametros: Map<String, Any>): UiComponent? {
    return when (tipo) {
        "text" -> mapTextComponent(parametros)
        "button" -> mapButtonComponent(parametros)
        "lista" -> mapListaComponent(parametros["itensLista"] as? List<Map<String, Any>> ?: emptyList())
        else -> null
    }
}

