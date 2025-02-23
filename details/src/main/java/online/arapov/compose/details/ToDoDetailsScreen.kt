package online.arapov.compose.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ToDoDetailsScreen(
    id: Int,
    text: String,
    onDelete: (Int) -> Unit,
    onUpdate: (Int, String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.padding(16.dp)
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "# $id",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )

            var isEditMode by remember {
                mutableStateOf(false)
            }
            var editedText by remember(text) {
                mutableStateOf(text)
            }
            Row(
                modifier = Modifier.padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        onUpdate(id, editedText)
                        isEditMode = !isEditMode
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    if (isEditMode) {
                        Text(text = "Save")
                    } else {
                        Text(text = "Edit")
                    }
                }
                Button(
                    onClick = { onDelete(id) },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Delete")
                }
            }
            if (isEditMode) {
                EditToDo(
                    text = text,
                    onUpdate = { editedText = it },
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                )
            } else {
                Text(
                    text = text,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Composable
private fun EditToDo(
    text: String,
    onUpdate: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var editedText by remember(text) {
        mutableStateOf(text)
    }
    TextField(
        value = editedText,
        onValueChange = {
            editedText = it
            onUpdate(it)
        },
        modifier = modifier
    )
}