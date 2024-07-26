package online.arapov.compose.list

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import online.arapov.compose.data.ToDoViewModel

class ListNode(
    buildContext: BuildContext,
    private val onShowDetails: (Int) -> Unit,
    private val viewModel: ToDoViewModel
) : Node(buildContext) {
    @Composable
    override fun View(modifier: Modifier) {
        TodoListScreen(
            list = viewModel.list,
            onClick = { onShowDetails(it) },
            addTodo = { viewModel.list += it }
        )
    }
}