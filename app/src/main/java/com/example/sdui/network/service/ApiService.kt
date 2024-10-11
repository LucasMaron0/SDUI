package com.example.sdui.network.service

import androidx.compose.ui.text.TextStyle
import com.example.sdui.compose.components.UiComponent
import com.example.sdui.compose.components.UiComponent.TextComponent
import com.example.sdui.compose.components.UiComponent.ButtonComponent
import com.example.sdui.compose.navigation.NavigationAction
import com.example.sdui.network.mapper.mapComponentes
import com.example.sdui.network.mapper.mapJson
import com.example.sdui.network.models.ApiResponse
import com.example.sdui.network.models.MappedResponse
import com.google.gson.Gson

fun fetchComponentsFromApi(apiUrl: String): MappedResponse {
    val response: String = when (apiUrl) {
        "OUTRO_ENDPOINT" -> """
            {
                "acao": "abrirTela",
                "componentes": [
                    {
                        "tipo": "text",
                        "parametros": {
                            "id": "3",
                            "text": "Esta é uma tela feita com Server Driven UI chamada a partir de outra tela SDUI!",
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
                    },
                    {
                        "tipo": "button",
                        "parametros": {
                            "id": "5",
                            "text": "Abrir tela com lista",
                            "onClickAction": "EXIBIR_TELA_SDUI",
                            "actionParameters": "COM_LISTA"
                        }
                    }
                ]
            }
        """.trimIndent()

        "COM_LISTA" -> """
    {
        "acao": "abrirTela",
        "componentes": [
            {
                "tipo": "lista",
                "parametros": {
                    "id": "lista1",
                    "itensLista": [
                        {
                            "tipo": "text",
                            "parametros": {
                                "id": "4",
                                "text": "Item 1 da lista",
                                "style": "default"
                            }
                        },
                        {
                            "tipo": "text",
                            "parametros": {
                                "id": "5",
                                "text": "Item 2 da lista",
                                "style": "default"
                            }
                        }
                    ]
                }
            }
        ]
    }
""".trimIndent()

        else -> """
            {
                "acao": "abrirTela",
                "componentes": [
                    {
                        "tipo": "text",
                        "parametros": {
                            "id": "3",
                            "text": "Bem-vindo, esta tela é feita com Server Driven UI!",
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

    return mapJson(response)
}



