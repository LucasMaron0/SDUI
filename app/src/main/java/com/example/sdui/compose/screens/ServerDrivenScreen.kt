import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sdui.compose.components.RenderButton
import com.example.sdui.compose.components.RenderList
import com.example.sdui.compose.components.RenderText
import com.example.sdui.compose.components.UiComponent

@Composable
fun ServerDrivenScreen(
    components: List<UiComponent>,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        components.forEach { component ->
            when (component) {
                is UiComponent.TextComponent -> RenderText(component)
                is UiComponent.ButtonComponent -> RenderButton(component, navController)
                is UiComponent.ListComponent -> RenderList(component, navController)
            }
        }
    }
}
