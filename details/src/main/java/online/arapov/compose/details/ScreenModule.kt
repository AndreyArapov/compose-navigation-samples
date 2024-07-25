package online.arapov.compose.details

import cafe.adriel.voyager.core.registry.screenModule
import online.arapov.compose.navigation.SharedScreen

val todoDetails = screenModule {
    register<SharedScreen.Details> {
        ToDoDetailsScreen(it.id)
    }
}