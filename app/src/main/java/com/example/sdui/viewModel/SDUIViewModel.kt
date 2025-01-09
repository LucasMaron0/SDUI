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
    var params: List<UiComponent> = emptyList()
        private set

    var action = mutableStateOf(NavigationAction.SHOW_HOME_SCREEN)
        private set

    var isLoading = mutableStateOf(false)
        private set

    fun fetchComponents(apiUrl: String) {
        viewModelScope.launch {
            isLoading.value = true
            val response = ApiClient.apiService.fetchComponentsFromApi(apiUrl)
            val mapped = mapJson(response);
            action.value = mapped.action
            params = mapped.params
            isLoading.value = false
        }
    }
}