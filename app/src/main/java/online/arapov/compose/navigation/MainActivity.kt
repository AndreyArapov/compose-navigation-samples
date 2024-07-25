package online.arapov.compose.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import online.arapov.compose.details.ToDoDetailsScreen
import online.arapov.compose.list.TodoListScreen
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
    val list = remember {
        mutableStateListOf<String>()
    }
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = TodoListScreen.route
    ) {
        composable(TodoListScreen.route) {
            TodoListScreen(
                list = list,
                onClick = { navController.navigate(ToDoDetailsScreen.getRoute(it)) },
                addTodo = { list.add(it) },
            )
        }

        composable(
            route = ToDoDetailsScreen.route,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments!!.getInt("id")
            if (id !in list.indices) {
                return@composable
            }
            ToDoDetailsScreen(
                id = id,
                text = list[id],
                onDelete = {
                    navController.popBackStack()
                    list.removeAt(it)
                },
                onUpdate = { i, v -> list[i] = v }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScreenPreview() {
    ComposeNavigationTheme {
        Navigation()
    }
}