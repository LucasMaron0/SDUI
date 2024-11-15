package com.example.sdui.compose.components

import androidx.compose.ui.text.TextStyle
import com.example.sdui.compose.navigation.NavigationAction
import com.example.sdui.network.models.ActionParameters

sealed class UiComponent {
    data class TextComponent(val id: String, val text: String, val style: TextStyle) : UiComponent()
    data class ButtonComponent(val id: String, val text: String, val onClickAction: NavigationAction, val actionParameters: ActionParameters?) : UiComponent()
    data class ListComponent(val items: List<UiComponent>) : UiComponent()
}