@file:Suppress("NAME_SHADOWING")

package app.compose.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Alarm
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import app.compose.R
import app.compose.ui.theme.aquaMarine
import app.compose.ui.theme.fadedAquaMarine
import app.compose.viewmodels.CountdownViewModel

@Preview
@Composable
fun HomeToolbarContent(modifier: Modifier = Modifier) {
    val viewModel: CountdownViewModel = viewModel()
    val dayCounter = viewModel.dayCounter.observeAsState(0).value
    val hourCounter = viewModel.hourCounter.observeAsState(0).value
    val minuteCounter = viewModel.minuteCounter.observeAsState(0).value

    val modifier = modifier
        .clip(RoundedCornerShape(50))
        .background(fadedAquaMarine)
        .padding(horizontal = 16.dp)

    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        Icon(Icons.Rounded.Alarm, tint = aquaMarine)
        CounterDownItem("$dayCounter", stringResource(R.string.days))
        CounterDownItem("$hourCounter", stringResource(R.string.hrs))
        CounterDownItem("$minuteCounter", stringResource(R.string.min))
    }
}

@Composable
fun CounterDownItem(text: String, label: String, modifier: Modifier = Modifier) {
    val modifier = modifier.padding(horizontal = 16.dp)

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = text, style = MaterialTheme.typography.body2)
        Text(text = label, style = MaterialTheme.typography.caption, color = aquaMarine)
    }
}