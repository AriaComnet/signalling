package com.hamidrezabashiri.signaling.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hamidrezabashiri.signaling.data.data_source.remote.RetrofitService
import com.hamidrezabashiri.signaling.data.repository.AuthenticationRepositoryImpl
import com.hamidrezabashiri.signaling.ui.screens.login.LoginScreen
import com.hamidrezabashiri.signaling.ui.screens.login.LoginViewModel
import com.hamidrezabashiri.signaling.ui.screens.lookup.LookUpScreen
import com.hamidrezabashiri.signaling.ui.screens.lookup.LookUpViewModel

object MainDestinations {
    const val HOME_ROUTE = "home"
    const val LOOKUP_ROUTE = "lookup"
    const val LOGIN_ROUTE = "login/"
}

@Composable
fun SignalingNavGraph(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    startDestination: String = MainDestinations.LOOKUP_ROUTE,
    repository: AuthenticationRepositoryImpl = AuthenticationRepositoryImpl(RetrofitService.getInstance())

) {

    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = "lookup") {
            val viewModel: LookUpViewModel = viewModel {
                LookUpViewModel(repository)
            }

            LookUpScreen(
                viewModel = viewModel,
                navController
            )
        }
        composable(route = "login/{phone}/{temp_token}") {
            val viewModel: LoginViewModel = viewModel {
                LoginViewModel(repository)
            }
            LoginScreen(
                viewModel = viewModel,
                it
            )
        }

    }

}

class MainActions(navController: NavHostController) {
    val navigateToHome: () -> Unit = {
        navController.popBackStack()
        navController.navigate(MainDestinations.HOME_ROUTE)
    }
    val navigateToLogin: () -> Unit = {
        navController.navigate(MainDestinations.LOGIN_ROUTE)
    }
    val upPress: () -> Unit = {
        navController.navigateUp()
    }

}