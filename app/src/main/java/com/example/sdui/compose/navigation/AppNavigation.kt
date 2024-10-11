// AppNavigation.kt
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.sdui.viewModel.SDUIViewModel

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
            route = "serverDriven/{apiUrl}",
            arguments = listOf(navArgument("apiUrl") { type = NavType.StringType })
        ) { backStackEntry ->
            // Recebe o apiUrl da navegação
            val apiUrl = backStackEntry.arguments?.getString("apiUrl") ?: ""

            // Chama fetchComponents com o apiUrl
            SDUIViewModel.fetchComponents(apiUrl)

            if (SDUIViewModel.isLoading.value) {
                CircularProgressIndicator(modifier = Modifier.fillMaxSize())
            } else {
                if(SDUIViewModel.acao.value == "abrirTela"){
                    ServerDrivenScreen(components = SDUIViewModel.components, navController = navController)
                }
            }
        }
    }
}
