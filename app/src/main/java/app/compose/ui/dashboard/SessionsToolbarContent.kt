package app.compose.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import app.compose.viewmodels.SessionsViewModel
import java.util.Calendar.DAY_OF_MONTH

@Preview
@Composable
fun SessionsToolbarContent(
    modifier: Modifier = Modifier,
    viewModel: SessionsViewModel = viewModel()
) {
    val dayModifier = modifier
        .clip(RoundedCornerShape(10))
        .background(fadedAquaMarine)
        .padding(horizontal = 8.dp)

    LazyRow(modifier = modifier) {
        itemsIndexed(items = sessionsViewModel.sessionDates.value,
            itemContent = { index, date ->
                Column(
                    modifier = dayModifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "$date",
                        style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colors.primary
                    )
                    Text(
                        text = "${stringResource(R.string.day)} ${index + 1}",
                        style = MaterialTheme.typography.overline,
                        color = aquaMarine
                    )
                }
            }
        )
    }
}