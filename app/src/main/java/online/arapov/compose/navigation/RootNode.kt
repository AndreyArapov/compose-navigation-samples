package online.arapov.compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.composable.Children
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.bumble.appyx.core.node.ParentNode
import com.bumble.appyx.navmodel.backstack.BackStack
import com.bumble.appyx.navmodel.backstack.operation.pop
import com.bumble.appyx.navmodel.backstack.operation.push
import com.bumble.appyx.navmodel.backstack.transitionhandler.rememberBackstackSlider
import online.arapov.compose.data.ToDoViewModel
import online.arapov.compose.details.DetailsNode
import online.arapov.compose.list.ListNode

class RootNode(
    buildContext: BuildContext,
    private val viewModel: ToDoViewModel,
    private val backStack: BackStack<NavTarget> = BackStack(
        initialElement = NavTarget.List,
        savedStateMap = buildContext.savedStateMap,
    )
) : ParentNode<NavTarget>(
    navModel = backStack,
    buildContext = buildContext
) {
    override fun resolve(navTarget: NavTarget, buildContext: BuildContext): Node {
        return when (navTarget) {
            is NavTarget.List -> ListNode(
                buildContext = buildContext,
                onShowDetails = { backStack.push(NavTarget.Details(it)) },
                viewModel = viewModel
            )

            is NavTarget.Details -> DetailsNode(
                buildContext = buildContext,
                idItem = navTarget.id,
                onFinish = { backStack.pop() },
                viewModel = viewModel
            )
        }
    }

    @Composable
    override fun View(modifier: Modifier) {
        Children(
            navModel = backStack,
            transitionHandler = rememberBackstackSlider()
        )
    }
}
