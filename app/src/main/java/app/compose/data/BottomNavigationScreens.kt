package app.compose.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.WatchLater
import androidx.compose.ui.graphics.vector.ImageVector
import app.compose.R

sealed class BottomNavigationScreens(val route: String, val labelId: Int, val icon: ImageVector) {
    object Home : BottomNavigationScreens("Home", R.string.home, Icons.Filled.Home)
    object Feed : BottomNavigationScreens("Feed", R.string.feed, Icons.Filled.Notifications)
    object Sessions : BottomNavigationScreens("Sessions", R.string.sessions, Icons.Filled.WatchLater)
    object About : BottomNavigationScreens("About", R.string.about, Icons.Filled.Star)
}