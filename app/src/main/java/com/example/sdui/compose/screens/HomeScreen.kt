import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import com.example.sdui.network.models.ActionParameters
import com.google.gson.Gson


@Composable
fun HomeScreen(onNavigateToServerDriven: (String) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home") }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Esta é a tela Home, construída de forma padrão.",
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Button(onClick = {
                    val encodedUrl = Uri.encode("sdui/1")
                    val actionParams = ActionParameters(apiUrl = encodedUrl)
                    val jsonParams = Gson().toJson(actionParams)
                    onNavigateToServerDriven(jsonParams)
                }) {
                    Text("Abrir tela em Server-Driven UI")
                    Icon(Icons.Default.ArrowForward, contentDescription = "Ir para SDUI")
                }
            }
        }
    )
}


