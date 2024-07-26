package online.arapov.compose.details

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import online.arapov.compose.data.ToDoViewModel

class DetailsNode(
    buildContext: BuildContext,
    private val idItem: Int,
    private val onFinish: () -> Unit,
    private val viewModel: ToDoViewModel
) : Node(buildContext) {
    @Composable
    override fun View(modifier: Modifier) {
        if (idItem !in viewModel.list.indices) return

        val text = viewModel.list[idItem]
        ToDoDetailsScreen(
            id = idItem,
            text = text,
            onDelete = {
                onFinish()
                viewModel.list.removeAt(idItem)
            },
            onUpdate = { i, v -> viewModel.list[i] = v }
        )
    }
}