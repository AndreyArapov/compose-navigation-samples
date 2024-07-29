package online.arapov.compose.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import online.arapov.compose.data.ToDoViewModel
import online.arapov.compose.details.ToDoDetailsScreen

object TodoListScreen {
    const val route = "list"
}

fun NavGraphBuilder.registerToDoList(navController: NavHostController) {
    composable(TodoListScreen.route) {
        val viewModel = viewModel<ToDoViewModel>()
        TodoListScreen(
            list = viewModel.list,
            onClick = { navController.navigate(ToDoDetailsScreen.getRoute(it)) },
            addTodo = { viewModel.list.add(it) },
        )
    }
}

@Composable
internal fun TodoListScreen(
    list: List<String>,
    onClick: (Int) -> Unit,
    addTodo: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            TodoInput(
                add = { addTodo(it) }
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(list) { i, v ->
                    Text(
                        text = v,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onClick(i) }
                            .height(56.dp)
                            .wrapContentHeight()
                            .padding(horizontal = 16.dp),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
private fun TodoInput(
    add: (String) -> Unit
) {
    var text by remember {
        mutableStateOf("")
    }
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .padding(8.dp)
                .weight(1f),
            maxLines = 2,
            placeholder = {
                Text(text = "To Do")
            }
        )

        Button(
            onClick = {
                add(text)
                text = ""
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "")
        }
    }
}
