package com.hamidrezabashiri.signaling.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hamidrezabashiri.signaling.ui.screens.home.HomeScreen
import com.hamidrezabashiri.signaling.ui.screens.login.LoginScreen
import com.hamidrezabashiri.signaling.ui.screens.lookup.LookUpScreen

object MainDestinations {
    const val HOME_ROUTE = "home"
    const val LOOKUP_ROUTE = "lookup"
    const val LOGIN_ROUTE = "login"
}

@Composable
fun SignalingNavGraph(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    startDestination: String = MainDestinations.LOOKUP_ROUTE,
) {

    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = MainDestinations.LOOKUP_ROUTE) {

            LookUpScreen(
                actions.navigateToLogin
            )
        }
        composable(route = "login/{phone}/{temp_token}") {
            LoginScreen(
                it,
                actions.navigateToHome
            )
        }
        composable(route = "home/{token}") {
            HomeScreen(navController)
        }

    }

}

class MainActions(navController: NavHostController) {
    val navigateToHome: (token: String) -> Unit = {
        navController.popBackStack()
        navController.navigate(MainDestinations.HOME_ROUTE + "/" + it)
    }
    val navigateToLogin: (phone: String, tempToken: String) -> Unit = { it, it2 ->
        navController.navigate(MainDestinations.LOGIN_ROUTE + "/" + it + "/" + it2)
    }
    val upPress: () -> Unit = {
        navController.navigateUp()
    }

}