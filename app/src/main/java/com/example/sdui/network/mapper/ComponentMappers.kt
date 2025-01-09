package com.example.sdui.network.mapper

import androidx.compose.ui.text.TextStyle
import com.example.sdui.compose.components.UiComponent
import com.example.sdui.network.models.ActionParameters

fun mapTextComponent(params: Map<String, Any>): UiComponent.TextComponent? {
    val id = params["id"] as? String ?: return null
    val text = params["text"] as? String ?: return null
    val style = when (params["style"] as? String) {
        "default" -> TextStyle.Default
        else -> TextStyle.Default
    }
    return UiComponent.TextComponent(id = id, text = text, style = style)
}

fun mapButtonComponent(params: Map<String, Any>): UiComponent.ButtonComponent? {
    val id = params["id"] as? String ?: ""
    val text = params["text"] as? String ?: ""
    val action = mapNavigationAction(params["onClickAction"] as? String)
    val actionParameters = mapActionParameters(params["actionParameters"] as? Map<String, Any>)
    return UiComponent.ButtonComponent(
        id = id,
        text = text,
        onClickAction = action,
        actionParameters = actionParameters
    )
}

fun mapActionParameters(actionParametersMap: Map<String, Any>?): ActionParameters? {
    return actionParametersMap?.let {
        val apiUrl = it["apiUrl"] as? String
        ActionParameters(apiUrl = apiUrl)
    }
}

fun mapListaComponent(listItens: List<Map<String, Any>>): UiComponent.ListComponent {
    val itens = listItens.mapNotNull { item ->
        mapSingleComponent(item["type"] as? String ?: "", item["params"] as? Map<String, Any> ?: emptyMap())
    }
    return UiComponent.ListComponent(itens)
}