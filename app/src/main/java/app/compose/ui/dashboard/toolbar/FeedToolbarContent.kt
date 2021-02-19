package app.compose.ui.dashboard.toolbar

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun FeedToolbarContent(modifier: Modifier = Modifier) = Text(
    text = "Feed",
    color = MaterialTheme.colors.primary,
    style = MaterialTheme.typography.h6,
    textAlign = TextAlign.Center,
    modifier = modifier
)