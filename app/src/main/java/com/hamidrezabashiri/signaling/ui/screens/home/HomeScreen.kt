package com.hamidrezabashiri.signaling.ui.screens.home

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.hamidrezabashiri.signaling.data.firebase.MessagingService

@Composable
fun HomeScreen(navHost: NavHostController) {
    val viewModel = hiltViewModel<HomeViewModel>()

    val token = rememberSaveable {
        mutableStateOf(navHost.currentBackStackEntry?.arguments?.getString("token"))
    }
    viewModel.establishConnection(
        token.value!!, MessagingService().getToken(navHost.context)!!
    )

}