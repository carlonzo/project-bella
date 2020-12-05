package it.carlom.feature_a

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun ExpandingCard(title: String, body: String) {
    var expanded by remember { mutableStateOf(false) }

    // describe the card for the current state of expanded
    Card {
        Column(
            Modifier
                .width(280.dp)
                .animateContentSize() // automatically animate size when it changes
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
        ) {
            Text(text = title)

            // content of the card depends on the current value of expanded
            if (expanded) {
                Text(text = body, Modifier.padding(top = 8.dp))
                // change expanded in response to click events
                IconButton(onClick = { expanded = false }, modifier = Modifier.fillMaxWidth()) {
                    Icon(Icons.Default.ExpandLess)
                }
            } else {
                // change expanded in response to click events
                IconButton(onClick = { expanded = true }, modifier = Modifier.fillMaxWidth()) {
                    Icon(Icons.Default.ExpandMore)
                }
            }
        }
    }
}

@Preview
@Composable
fun TestCard() {

    val text = """
            You can build an extension function for Jetpack Compose to read other observable types if your app uses a custom observable class. See the implementation of the builtins for examples of how to do this. Any object that allows Jetpack Compose to subscribe to every change can be converted to State<T> and read by a composable.

            You can also build integration layers for non-observable state objects by using invalidate to manually trigger recomposition. This should be reserved for situations where you must interoperate with a non-observable type. Using invalidate is easy to get wrong and tends to lead to complex code that's harder to read than the same code using observable state objects.
        """.trimIndent()

    ExpandingCard("Title", text)
}

