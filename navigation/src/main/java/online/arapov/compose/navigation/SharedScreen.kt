package online.arapov.compose.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed class SharedScreen : ScreenProvider {
    data object List : SharedScreen()
    class Details(val id: Int) : SharedScreen()
}