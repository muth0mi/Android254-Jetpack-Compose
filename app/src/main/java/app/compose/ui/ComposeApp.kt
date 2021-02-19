package app.compose.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import app.compose.R
import app.compose.data.BottomNavigationScreens
import app.compose.ui.dashboard.*
import app.compose.ui.theme.ComposeTheme
import app.compose.ui.theme.black
import app.compose.ui.theme.white
import app.compose.viewmodels.SessionsViewModel

lateinit var sessionsViewModel: SessionsViewModel

@Preview
@Composable
fun ComposeApp(name: String = "Compose") {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
    val bottomNavigationItems = listOf(
        BottomNavigationScreens.Home,
        BottomNavigationScreens.Feed,
        BottomNavigationScreens.Sessions,
        BottomNavigationScreens.About
    )

    sessionsViewModel = viewModel()

    ComposeTheme {
        Scaffold(
            topBar = { TopBar(navController, currentRoute) },
            bodyContent = { Screens(navController) },
            bottomBar = { BottomBar(navController, currentRoute, bottomNavigationItems) }
        )
    }
}

@Composable
private fun TopBar(navController: NavHostController, currentRoute: String?) {
    val modifier = Modifier.padding(6.dp).fillMaxHeight()

    TopAppBar(
        elevation = 1.dp,
        backgroundColor = MaterialTheme.colors.background,
        navigationIcon = { IconButton(onClick = {}) { Icon(Icons.Filled.Info, "") } },
        actions = { IconButton(onClick = {}) { Icon(Icons.Filled.AccountCircle, "") } },
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth().padding(end = 24.dp)
            ) {
                when (currentRoute) {
                    BottomNavigationScreens.Home.route ->
                        HomeToolbarContent(modifier)
                    BottomNavigationScreens.Feed.route ->
                        FeedToolbarContent(modifier)
                    BottomNavigationScreens.Sessions.route ->
                        SessionsToolbarContent(modifier, sessionsViewModel)
                    BottomNavigationScreens.About.route ->
                        AboutToolbarContent(modifier)
                    else ->
                        Text(text = stringResource(id = R.string.app_name))
                }
            }
        }
    )
}

@Composable
private fun Screens(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavigationScreens.Home.route) {
        composable(BottomNavigationScreens.Home.route) { Text(it.toString()) }
        composable(BottomNavigationScreens.Feed.route) { Text(it.toString()) }
        composable(BottomNavigationScreens.Sessions.route) { SessionsScreen(sessionsViewModel) }
        composable(BottomNavigationScreens.About.route) { Text(it.toString()) }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController, currentRoute: String?, items: List<BottomNavigationScreens>
) {
    val backgroundColor = if (isSystemInDarkTheme()) MaterialTheme.colors.surface else black

    BottomNavigation(backgroundColor = backgroundColor) {
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(screen.icon, "") },
                label = { Text(stringResource(id = screen.labelId)) },
                selected = currentRoute == screen.route,
                alwaysShowLabels = true,
                selectedContentColor = MaterialTheme.colors.secondary,
                unselectedContentColor = white,
                onClick = { if (currentRoute != screen.route) navController.navigate(screen.route) }
            )
        }
    }
}