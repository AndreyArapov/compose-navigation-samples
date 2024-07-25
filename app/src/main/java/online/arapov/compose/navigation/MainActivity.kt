package online.arapov.compose.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.navigation.navGraph
import online.arapov.compose.data.ToDoViewModel
import online.arapov.compose.navigation.ui.screens.NavGraphs
import online.arapov.compose.navigation.ui.theme.ComposeNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeNavigationTheme {
                Navigation()
            }
        }
    }
}

@Composable
private fun Navigation() {
    val viewModel = viewModel(ToDoViewModel::class)
    DestinationsNavHost(
        navGraph = NavGraphs.main,
        dependenciesContainerBuilder = {
            navGraph(navGraph = NavGraphs.main) {
                dependency(viewModel)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun ScreenPreview() {
    ComposeNavigationTheme {
        Navigation()
    }
}