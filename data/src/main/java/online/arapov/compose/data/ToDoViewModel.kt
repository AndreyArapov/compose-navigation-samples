package online.arapov.compose.data

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class ToDoViewModel : ViewModel() {
    val list = mutableStateListOf<String>()
}