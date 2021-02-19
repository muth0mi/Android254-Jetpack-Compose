package app.compose.ui.dashboard.toolbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.compose.R
import app.compose.ui.sessionsViewModel
import app.compose.ui.theme.aquaMarine
import app.compose.ui.theme.fadedAquaMarine
import app.compose.ui.theme.yellow
import app.compose.viewmodels.SessionsViewModel

@Preview
@Composable
fun SessionsToolbarContent(
    modifier: Modifier = Modifier,
    viewModel: SessionsViewModel = viewModel()
) {
    LazyRow(modifier = modifier) {
        itemsIndexed(items = sessionsViewModel.sessionDates.value,
            itemContent = { index, date ->
                val dayStyle = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                val dayLabel = "${stringResource(R.string.day)} ${index + 1}"
                val dayLabelStyle = MaterialTheme.typography.overline

                val bg = if (viewModel.selectedDate.value == date) yellow else fadedAquaMarine
                val dayModifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 8.dp)
                    .clip(RoundedCornerShape(15))
                    .background(bg)
                    .padding(horizontal = 8.dp)
                    .clickable { viewModel.selectDate(date) }

                Column(dayModifier, Arrangement.SpaceEvenly, Alignment.CenterHorizontally) {
                    Text(text = "$date", style = dayStyle, color = MaterialTheme.colors.primary)
                    Text(text = dayLabel, style = dayLabelStyle, color = aquaMarine)
                }
            }
        )
    }
}