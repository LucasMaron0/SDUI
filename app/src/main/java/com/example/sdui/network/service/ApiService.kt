package com.example.sdui.network.service

import androidx.compose.ui.text.TextStyle
import com.example.sdui.compose.components.UiComponent.TextComponent
import com.example.sdui.compose.components.UiComponent.ButtonComponent
import com.example.sdui.compose.navigation.NavigationAction
import com.example.sdui.network.models.ApiResponse
import com.example.sdui.network.models.MappedResponse
import com.google.gson.Gson


fun fetchComponentsFromApi(apiUrl: String): MappedResponse {
    var response: String
    if (apiUrl == "OUTRO_ENDPOINT") {
        response =  """
    {
        "acao": "abrirTela",
        "componentes": [
            {
                "tipo": "text",
                "parametros": {
                    "id": "3",
                    "text": "Está é tela  feita com Server Driven UI chamada a partir de outra tela SDUI!",
                    "style": "default"
                }
            },
            {
                "tipo": "button",
                "parametros": {
                    "id": "1",
                    "text": "Voltar para Home",
                    "onClickAction": "EXIBIR_HOME",
                    "actionParameters": ""
                }
            },
            {
                "tipo": "button",
                "parametros": {
                    "id": "2",
                    "text": "Abrir a primeira tela SDUI novamente",
                    "onClickAction": "EXIBIR_TELA_SDUI",
                    "actionParameters": ""
                }
            }
        ]
    }
    """.trimIndent()
    } else {
        response = """
    {
        "acao": "abrirTela",
        "componentes": [
            {
                "tipo": "text",
                "parametros": {
                    "id": "3",
                    "text": "Bem-vindo, está tela é feita com Server Driven UI!",
                    "style": "default"
                }
            },
            {
                "tipo": "button",
                "parametros": {
                    "id": "1",
                    "text": "Voltar para Home",
                    "onClickAction": "EXIBIR_HOME",
                    "actionParameters": ""
                }
            },
            {
                "tipo": "button",
                "parametros": {
                    "id": "2",
                    "text": "Abrir OUTRA tela SDUI",
                    "onClickAction": "EXIBIR_TELA_SDUI",
                    "actionParameters": "OUTRO_ENDPOINT"
                }
            }
        ]
    }
    """.trimIndent()
    }


    // Deserializa o JSON para `ApiResponse`
    val apiResponse: ApiResponse = Gson().fromJson(response, ApiResponse::class.java)

    // Mapeia os componentes em `UiComponent`
    val mappedComponents = apiResponse.componentes.mapNotNull { componentData ->
        when (componentData.tipo) {
            "text" -> {
                val id = componentData.parametros["id"] as? String ?: return@mapNotNull null
                val text = componentData.parametros["text"] as? String ?: return@mapNotNull null
                val style = when (componentData.parametros["style"] as? String) {
                    "default" -> TextStyle.Default
                    else -> TextStyle.Default
                }
                TextComponent(id = id, text = text, style = style)
            }
            "button" -> {
                val id = componentData.parametros["id"] as? String ?:""
                val text = componentData.parametros["text"] as? String ?: ""
                val action = when (componentData.parametros["onClickAction"] as? String) {
                    "EXIBIR_HOME" -> NavigationAction.EXIBIR_HOME
                    "EXIBIR_TELA_SDUI" -> NavigationAction.EXIBIR_TELA_SDUI
                    else -> NavigationAction.EXIBIR_HOME
                }
                val actionParameters = componentData.parametros["actionParameters"] as? String ?:""
                ButtonComponent(id = id, text = text, onClickAction = action, actionParameters = actionParameters)
            }
            else -> null
        }
    }

    return MappedResponse(apiResponse.acao, mappedComponents)
}
