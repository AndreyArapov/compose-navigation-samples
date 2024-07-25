package online.arapov.compose.navigation

import com.ramcosta.composedestinations.annotation.ExternalNavGraph
import com.ramcosta.composedestinations.annotation.NavHostGraph
import com.ramcosta.composedestinations.generated.details.navgraphs.DetailsGraph
import com.ramcosta.composedestinations.generated.list.navgraphs.ListGraph

@NavHostGraph
annotation class MainGraph {

    @ExternalNavGraph<ListGraph>(start = true)
    @ExternalNavGraph<DetailsGraph>
    companion object Includes
}