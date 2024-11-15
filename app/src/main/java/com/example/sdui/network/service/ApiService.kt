package com.example.sdui.network.service

import com.example.sdui.network.mapper.mapJson
import com.example.sdui.network.models.MappedResponse

fun fetchComponentsFromApi(apiUrl: String): MappedResponse {
    val response: String = when (apiUrl) {
        "OUTRO_ENDPOINT" -> """
            {
                "acao": "EXIBIR_TELA_SDUI",
                "parametros": [
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
                            "actionParameters": {}
                        }
                    },
                    {
                        "tipo": "button",
                        "parametros": {
                            "id": "2",
                            "text": "Abrir a primeira tela SDUI novamente",
                            "onClickAction": "EXIBIR_TELA_SDUI",
                            "actionParameters": {}
                        }
                    },
                    {
                        "tipo": "button",
                        "parametros": {
                            "id": "5",
                            "text": "Abrir tela com lista",
                            "onClickAction": "EXIBIR_TELA_SDUI",
                            "actionParameters":{
                                "apiUrl": "COM_LISTA"
                             }
                        }
                    }
                ]
            }
        """.trimIndent()

        "COM_LISTA" -> """
    {
        "acao": "EXIBIR_TELA_SDUI",
        "parametros": [
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
                "acao": "EXIBIR_TELA_SDUI",
                "parametros": [
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
                            "actionParameters": {}
                        }
                    },
                    {
                        "tipo": "button",
                        "parametros": {
                            "id": "2",
                            "text": "Abrir OUTRA tela SDUI",
                            "onClickAction": "EXIBIR_TELA_SDUI",
                            "actionParameters": {
                                "apiUrl": "OUTRO_ENDPOINT"
                            }
                        }
                    }
                ]
            }
        """.trimIndent()
    }

    return mapJson(response)
}



