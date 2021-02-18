package app.compose.ui.dashboard

import android.util.Log
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.viewModel
import app.compose.viewmodels.SessionsViewModel

@Preview
@Composable
fun SessionsToolbarContent(modifier: Modifier = Modifier) {
    val viewModel: SessionsViewModel = viewModel()
    Log.e("SessionsToolbarContent", "$viewModel")

    val dayCounter = viewModel.dayCounter.observeAsState("").value
    Text(dayCounter)
}