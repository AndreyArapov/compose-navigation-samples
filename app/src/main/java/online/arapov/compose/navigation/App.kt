package online.arapov.compose.navigation

import android.app.Application
import cafe.adriel.voyager.core.registry.ScreenRegistry
import online.arapov.compose.details.todoDetails
import online.arapov.compose.list.todoList

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        ScreenRegistry {
            todoList()
            todoDetails()
        }
    }
}