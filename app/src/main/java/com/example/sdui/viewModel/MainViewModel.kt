package com.example.sdui.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sdui.compose.components.UiComponent
import com.example.sdui.network.service.fetchComponentsFromApi
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var components: List<UiComponent> = emptyList()
        private set

    var acao = mutableStateOf("")
        private set


    // Alterando isLoading para um MutableState
    var isLoading = mutableStateOf(false)
        private set

    fun fetchComponents(apiUrl: String) {
        viewModelScope.launch {
            isLoading.value = true
            val response = fetchComponentsFromApi(apiUrl) // Chamada de API
            components = response.componentes // Lista de componentes
            acao.value = response.acao // Valor de "acao" do JSON
            isLoading.value = false
        }
    }
}