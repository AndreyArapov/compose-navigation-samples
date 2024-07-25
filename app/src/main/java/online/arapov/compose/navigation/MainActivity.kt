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
import online.arapov.compose.list.TodoListScreen
import online.arapov.compose.navigation.ui.theme.ComposeNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeNavigationTheme {
                val list = remember {
                    mutableStateListOf<String>()
                }
                TodoListScreen(
                    list = list,
                    onClick = {},
                    addTodo = { list.add(it) },
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScreenPreview() {
    ComposeNavigationTheme {
        val list = remember {
            mutableStateListOf<String>()
        }
        TodoListScreen(
            list = list,
            onClick = {},
            addTodo = { list.add(it) },
            modifier = Modifier
                .fillMaxSize()
        )
    }
}