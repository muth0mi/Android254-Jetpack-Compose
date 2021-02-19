package app.compose.ui.dashboard

import android.util.Log
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import app.compose.viewmodels.SessionsViewModel

@Preview
@Composable
fun SessionsScreen(viewModel: SessionsViewModel = viewModel()) {
    Log.e("SessionsScreen", "$viewModel")

    val dayCounter = viewModel.dayCounter.observeAsState("").value

    TextField(dayCounter, onValueChange = { viewModel.updateCountdown(it) })
}