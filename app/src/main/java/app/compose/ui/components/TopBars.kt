package app.compose.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun HomeToolbarContent(modifier: Modifier = Modifier)= Text("Sessions")

@Composable
fun FeedToolbarContent(modifier: Modifier = Modifier) = Text(
    text = "Feed",
    color = MaterialTheme.colors.primary,
    style = MaterialTheme.typography.h6,
    textAlign = TextAlign.Center,
    modifier = modifier
)

@Composable
fun SessionsToolbarContent(modifier: Modifier = Modifier) = Text("Sessions")

@Composable
fun AboutToolbarContent(modifier: Modifier = Modifier) = Text("About")
