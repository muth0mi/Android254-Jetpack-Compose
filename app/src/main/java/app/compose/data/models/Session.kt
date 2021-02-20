package app.compose.data.models

import java.util.Calendar

data class Session(
    val startTime: Calendar,
    val durationInMinutes: Int,
    val venue: String,
    val title: String,
    val description: String,
    val speaker: Speaker,
    val banner: String
)