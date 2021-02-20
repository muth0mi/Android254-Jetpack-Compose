package app.compose.ui.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.compose.R
import app.compose.data.models.Session
import app.compose.ui.custom.Pager
import app.compose.ui.custom.PagerState
import app.compose.ui.theme.*
import app.compose.viewmodels.SessionsViewModel
import java.lang.String
import java.util.*

@Preview
@Composable
fun SessionsScreen(viewModel: SessionsViewModel = viewModel()) {
    val pagerState = remember { PagerState(pages = viewModel.sessionDates.value.lastIndex) }

    Pager(state = pagerState) {
        viewModel.selectDate(viewModel.sessionDates.value[this.currentPage])
        LazyColumn(modifier = Modifier.fillMaxSize().padding(vertical = 8.dp)) {
            items(
                items = viewModel.sessions.value,
                itemContent = { SessionsCard(it) }
            )
        }
    }
}


@Composable
fun SessionsCard(session: Session) {
    Card(
        elevation = 1.dp,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SessionTime(session.startTime)
            SessionDetails(session)
            SessionFavouriteIcon()
        }
    }
}

@Composable
fun SessionTime(startTime: Calendar) {
    val startHour = startTime.get(Calendar.HOUR_OF_DAY)
    val startMinute = startTime.get(Calendar.MINUTE)
    val amPm = startTime.getDisplayName(Calendar.AM_PM, Calendar.SHORT, Locale.getDefault())!!

    Column(
        modifier = Modifier.padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = String.format("%02d", startHour),
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.h5.copy(textAlign = TextAlign.Justify)
        )
        Text(
            text = String.format("%02d", startMinute),
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.body1.copy(
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Justify
            )
        )
        Text(
            modifier = Modifier.padding(top = 16.dp).rotate(270F),
            text = amPm,
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.overline
        )
    }
}

@Composable
fun SessionDetails(session: Session) {
    Column(modifier = Modifier.padding(12.dp).fillMaxWidth(.85F)) {
        Text(
            text = session.title,
            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
        )

        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = session.description,
            style = MaterialTheme.typography.body2,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )

        Row(modifier = Modifier.padding(top = 8.dp)) {
            Text(
                text = "${session.durationInMinutes} ${stringResource(R.string.minutes)}",
                style = MaterialTheme.typography.caption,
                color = darkGrey
            )
            Text(
                text = " | ",
                style = MaterialTheme.typography.caption,
                color = darkGrey
            )
            Text(
                text = session.venue,
                style = MaterialTheme.typography.caption,
                color = darkGrey
            )
        }

        Row(
            modifier = Modifier.padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Filled.Android, "", tint = green)
            Spacer(modifier = Modifier.preferredWidth(6.dp))
            Text(
                text = session.speaker.name,
                style = MaterialTheme.typography.caption,
                color = green
            )
        }
    }
}

@Composable
fun SessionFavouriteIcon() {
    IconButton(
        modifier = Modifier.padding(horizontal = 12.dp),
        onClick = {}
    ) { Icon(Icons.Rounded.Star, "") }
}