package app.compose.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import app.compose.R

sealed class BottomNavigationScreens(val route: String, val labelId: Int, val icon: ImageVector) {
    object Home : BottomNavigationScreens("Home", R.string.home, Icons.Filled.Home)
    object Feed : BottomNavigationScreens("Feed", R.string.feed, Icons.Filled.Notifications)
    object Sessions : BottomNavigationScreens("Sessions", R.string.sessions, Icons.Filled.Schedule)
    object About : BottomNavigationScreens("About", R.string.about, Icons.Filled.Star)
}