package app.compose.ui.dashboard

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.compose.R
import app.compose.ui.theme.*
import app.compose.viewmodels.HomeViewModel
import app.compose.viewmodels.SessionsViewModel
import dev.chrisbanes.accompanist.coil.CoilImage
import java.lang.String
import java.util.*

@Preview
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel(),
    sessionsViewModel: SessionsViewModel = viewModel()
) {
    ScrollableColumn(modifier = Modifier.fillMaxSize().padding(top = 8.dp, bottom = 56.dp)) {
        PromoCard()
        CallForSpeakers()
        KeynoteSpeaker(homeViewModel)
        Box(modifier = Modifier.fillMaxWidth().preferredHeight(1.dp).background(lightGrey))
        SessionList(sessionsViewModel)
        Box(modifier = Modifier.fillMaxWidth().preferredHeight(1.dp).background(lightGrey))
        SponsorList(homeViewModel)
        Box(modifier = Modifier.fillMaxWidth().preferredHeight(1.dp).background(lightGrey))
        OrganizerList(homeViewModel)
    }
}

@Composable
fun PromoCard() {
    Box(Modifier.fillMaxWidth().fillMaxHeight().padding(horizontal = 8.dp)) {
        Card(
            elevation = 0.dp,
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
        ) {
            Image(
                painterResource(id = R.drawable.promotion_background), "",
                modifier = Modifier.fillMaxWidth().alpha(.5F).heightIn().padding(horizontal = 8.dp)
            )
        }
        Image(
            painter = painterResource(id = R.drawable.promotion_foreground), "",
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun CallForSpeakers() {
    Card(
        elevation = 8.dp,
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        backgroundColor = lightPurple
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(12.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.horn), "",
                modifier = Modifier.padding(12.dp).preferredHeight(24.dp)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = "Call for speakers", style = MaterialTheme.typography.h6)
                Text(text = "Apply to be a speaker", style = MaterialTheme.typography.caption)
            }
            IconButton(onClick = { }) { Icon(Icons.Filled.PlayArrow, "") }
        }
    }
}

@Composable
fun KeynoteSpeaker(homeViewModel: HomeViewModel) {
    val keynoteSpeaker = homeViewModel.keynoteSpeaker.value

    Card(elevation = 8.dp, modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = stringResource(R.string.keynoteSpkr),
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            CoilImage(
                data = keynoteSpeaker.avatar,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .preferredSize(84.dp)
                    .clip(CircleShape)
                    .border(8.dp, bermuda, CircleShape)
                    .padding(16.dp)
            )
            Text(
                text = keynoteSpeaker.name,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            Text(
                text = "Apply to be a speaker",
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.body2.copy(textDecoration = TextDecoration.Underline)
            )
        }
    }
}

@Composable
fun SessionList(sessionsViewModel: SessionsViewModel) {
    Card(elevation = 0.dp, modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            ) {
                Text(
                    text = stringResource(R.string.sessions),
                    style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = stringResource(R.string.viewAll),
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.primary
                )
            }

            LazyRow(modifier = Modifier.padding(vertical = 8.dp)) {
                itemsIndexed(items = sessionsViewModel.sessions.value,
                    itemContent = { index, session ->
                        val t = String.format("%02d", session.startTime.get(Calendar.HOUR_OF_DAY)) +
                                ":" + String.format("%02d", session.startTime.get(Calendar.MINUTE))

                        val sessionModifier = Modifier
                            .preferredWidth(364.dp)
                            .preferredHeight(244.dp)
                            .padding(8.dp)

                        Card(elevation = 2.dp, modifier = sessionModifier) {
                            Column(verticalArrangement = Arrangement.Top) {
                                CoilImage(
                                    data = session.banner,
                                    contentDescription = "",
                                    modifier = Modifier.preferredWidth(360.dp)
                                        .preferredHeight(120.dp),
                                    contentScale = ContentScale.FillWidth,
                                )
                                Row(
                                    modifier = Modifier.padding(12.dp).preferredWidth(360.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        color = MaterialTheme.colors.primary,
                                        text = t,
                                        style = MaterialTheme.typography.caption
                                    )
                                    Text(
                                        color = MaterialTheme.colors.primary,
                                        text = session.venue,
                                        style = MaterialTheme.typography.caption
                                    )
                                }
                                Text(
                                    modifier = Modifier.padding(horizontal = 12.dp),
                                    style = MaterialTheme.typography.body2,
                                    text = session.description,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun SponsorList(homeViewModel: HomeViewModel) {
    Card(elevation = 0.dp, modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            ) {
                Text(
                    text = stringResource(R.string.speakers),
                    style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = stringResource(R.string.viewAll),
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.primary
                )
            }

            LazyRow(modifier = Modifier.padding(vertical = 8.dp)) {
                itemsIndexed(items = homeViewModel.speakers.value,
                    itemContent = { index, speaker ->

                        val sessionModifier = Modifier
                            .preferredWidth(124.dp)
                            .preferredHeight(124.dp)
                            .padding(8.dp)

                        Card(
                            elevation = 0.dp,
                            modifier = sessionModifier,
                            backgroundColor = fadedAquaMarine
                        ) {
                            Column(
                                verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                CoilImage(
                                    data = speaker.avatar,
                                    contentDescription = "",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .preferredSize(72.dp)
                                        .clip(CircleShape)
                                        .border(4.dp, bermuda, CircleShape)
                                        .padding(8.dp)
                                )
                                Text(
                                    style = MaterialTheme.typography.caption,
                                    text = speaker.name,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun OrganizerList(homeViewModel: HomeViewModel) {
    Card(elevation = 8.dp, modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = stringResource(R.string.organizedBy),
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            Text(
                text = "Comming Soon",
                style = MaterialTheme.typography.h3,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }
    }
}