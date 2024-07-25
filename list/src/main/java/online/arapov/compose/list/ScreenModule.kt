package online.arapov.compose.list

import cafe.adriel.voyager.core.registry.screenModule
import online.arapov.compose.navigation.SharedScreen

val todoList = screenModule {
    register<SharedScreen.List> {
        TodoListScreen()
    }
}