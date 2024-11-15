import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.sdui.compose.navigation.NavigationAction
import com.example.sdui.network.models.ActionParameters
import com.example.sdui.network.models.MappedResponse
import com.example.sdui.viewModel.SDUIViewModel
import com.google.gson.Gson

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val SDUIViewModel: SDUIViewModel = viewModel()

    NavHost(navController, startDestination = "home") {
        composable("home") {
            HomeScreen(onNavigateToServerDriven = { apiUrl ->
                navController.navigate("serverDriven/$apiUrl")
            })
        }
        composable(
            route = "serverDriven/{jsonParams}",
            arguments = listOf(navArgument("jsonParams") { type = NavType.StringType })
        ) { backStackEntry ->

            val jsonParams = backStackEntry.arguments?.getString("jsonParams") ?: ""
            val actionParameters = try {
                Gson().fromJson(jsonParams, ActionParameters::class.java)
            } catch (e: Exception) {
                null
            }

            val apiUrl = actionParameters?.apiUrl?.takeIf { it.isNotBlank() } ?: ""

            SDUIViewModel.fetchComponents(apiUrl)

            if (SDUIViewModel.isLoading.value) {
                CircularProgressIndicator(modifier = Modifier.fillMaxSize())
            } else {
                if (SDUIViewModel.acao.value == NavigationAction.EXIBIR_TELA_SDUI) {
                    ServerDrivenScreen(components = SDUIViewModel.parametros, navController = navController)
                }
            }
        }
    }
}

