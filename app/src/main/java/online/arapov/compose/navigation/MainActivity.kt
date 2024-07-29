package online.arapov.compose.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import online.arapov.compose.details.ToDoDetailsScreen
import online.arapov.compose.details.registerToDoDetails
import online.arapov.compose.list.TodoListScreen
import online.arapov.compose.list.registerToDoList
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
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = TodoListScreen.route
    ) {
        registerToDoList(navController)

        registerToDoDetails(navController)
    }
}

@Preview(showBackground = true)
@Composable
private fun ScreenPreview() {
    ComposeNavigationTheme {
        Navigation()
    }
}