package com.example.sdui.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sdui.compose.components.UiComponent
import com.example.sdui.compose.navigation.NavigationAction
import com.example.sdui.network.ApiClient
import com.example.sdui.network.mapper.mapJson
import kotlinx.coroutines.launch

class SDUIViewModel : ViewModel() {
    var parametros: List<UiComponent> = emptyList()
        private set

    var acao = mutableStateOf(NavigationAction.EXIBIR_HOME)
        private set

    var isLoading = mutableStateOf(false)
        private set

    fun fetchComponents(apiUrl: String) {
        viewModelScope.launch {
//            isLoading.value = true
            val response = ApiClient.apiService.fetchComponentsFromApi(apiUrl)
            val mapped = mapJson(response);
            acao.value = mapped.acao
            parametros = mapped.parametros
            isLoading.value = false
        }
    }
}