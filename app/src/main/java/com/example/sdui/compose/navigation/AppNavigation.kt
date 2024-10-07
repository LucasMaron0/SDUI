// AppNavigation.kt
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.sdui.viewModel.MainViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val mainViewModel: MainViewModel = viewModel()

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
            mainViewModel.fetchComponents(apiUrl)

            if (mainViewModel.isLoading.value) {
                CircularProgressIndicator(modifier = Modifier.fillMaxSize())
            } else {
                if(mainViewModel.acao.value == "abrirTela"){
                    ServerDrivenScreen(components = mainViewModel.components, navController = navController)
                }
            }
        }
    }
}
