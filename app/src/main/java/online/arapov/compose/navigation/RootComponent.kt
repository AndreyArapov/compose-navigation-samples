package online.arapov.compose.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import online.arapov.compose.data.ToDoViewModel
import online.arapov.compose.details.ToDoDetailsComponent
import online.arapov.compose.details.ToDoDetailsScreen
import online.arapov.compose.list.TodoListComponent
import online.arapov.compose.list.TodoListScreen

class RootComponent(
    componentContext: ComponentContext,
) : ComponentContext by componentContext {
    private val navigation = StackNavigation<Config>()

    val stack: Value<ChildStack<*, Child>> =
        childStack(
            source = navigation,
            serializer = Config.serializer(),
            initialConfiguration = Config.List,
            handleBackButton = true,
            childFactory = ::child,
        )

    private fun child(config: Config, childComponentContext: ComponentContext): Child =
        when (config) {
            is Config.List -> Child.List(listComponent(childComponentContext))
            is Config.Details -> Child.Details(detailsComponent(childComponentContext, config.id))
        }

    private fun listComponent(componentContext: ComponentContext): TodoListComponent =
        TodoListComponent(
            componentContext = componentContext,
            onShowDetails = { navigation.push(Config.Details(it)) },
        )

    private fun detailsComponent(
        componentContext: ComponentContext,
        id: Int
    ): ToDoDetailsComponent =
        ToDoDetailsComponent(
            componentContext = componentContext,
            id = id,
            onFinish = navigation::pop
        )

    @Serializable
    private sealed interface Config {
        @Serializable
        data object List : Config

        @Serializable
        data class Details(
            val id: Int
        ) : Config
    }

    sealed class Child {
        class List(val component: TodoListComponent) : Child()
        class Details(val component: ToDoDetailsComponent) : Child()
    }
}

@Composable
fun RootContent(
    component: RootComponent,
    modifier: Modifier = Modifier,
) {
    val viewModel = viewModel<ToDoViewModel>()
    Children(
        stack = component.stack,
        modifier = modifier.fillMaxSize(),
        animation = stackAnimation(fade() + scale())
    ) {
        when (val instance = it.instance) {
            is RootComponent.Child.List -> TodoListScreen(
                component = instance.component,
                viewModel = viewModel
            )

            is RootComponent.Child.Details -> ToDoDetailsScreen(
                component = instance.component,
                viewModel = viewModel
            )
        }
    }
}