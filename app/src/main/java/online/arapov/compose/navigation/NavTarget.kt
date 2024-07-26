package online.arapov.compose.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class NavTarget : Parcelable {
    @Parcelize
    data object List : NavTarget()

    @Parcelize
    data class Details(
        val id: Int
    ) : NavTarget()
}