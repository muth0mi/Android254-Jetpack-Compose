package app.compose.ui

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import app.compose.ui.theme.ComposeTheme

@Preview
@Composable
fun ComposeApp(name: String = "Compose") {
    ComposeTheme {
        Scaffold(
            bodyContent = { Text(text = "Hello $name!") }
        )
    }
}