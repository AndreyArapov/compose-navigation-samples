package online.arapov.compose.navigation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumble.appyx.core.integration.NodeHost
import com.bumble.appyx.core.integrationpoint.NodeActivity
import online.arapov.compose.data.ToDoViewModel
import online.arapov.compose.navigation.ui.theme.ComposeNavigationTheme

class MainActivity : NodeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = viewModel<ToDoViewModel>()
            ComposeNavigationTheme {
                NodeHost(integrationPoint = appyxV1IntegrationPoint) {
                    RootNode(it, viewModel)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScreenPreview() {
    ComposeNavigationTheme {

    }
}