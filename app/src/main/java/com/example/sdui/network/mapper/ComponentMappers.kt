package com.example.sdui.network.mapper

import androidx.compose.ui.text.TextStyle
import com.example.sdui.compose.components.UiComponent

fun mapTextComponent(parametros: Map<String, Any>): UiComponent.TextComponent? {
    val id = parametros["id"] as? String ?: return null
    val text = parametros["text"] as? String ?: return null
    val style = when (parametros["style"] as? String) {
        "default" -> TextStyle.Default
        else -> TextStyle.Default
    }
    return UiComponent.TextComponent(id = id, text = text, style = style)
}

fun mapButtonComponent(parametros: Map<String, Any>): UiComponent.ButtonComponent? {
    val id = parametros["id"] as? String ?: ""
    val text = parametros["text"] as? String ?: ""
    val action = mapNavigationAction(parametros["onClickAction"] as? String)
    val actionParameters = parametros["actionParameters"] as? String ?: ""
    return UiComponent.ButtonComponent(
        id = id,
        text = text,
        onClickAction = action,
        actionParameters = actionParameters
    )
}

fun mapListaComponent(itensLista: List<Map<String, Any>>): UiComponent.ListComponent {
    val itens = itensLista.mapNotNull { item ->
        mapSingleComponent(item["tipo"] as? String ?: "", item["parametros"] as? Map<String, Any> ?: emptyMap())
    }
    return UiComponent.ListComponent(itens)
}