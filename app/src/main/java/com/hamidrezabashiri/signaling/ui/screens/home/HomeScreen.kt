package com.hamidrezabashiri.signaling.ui.screens.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry

@Composable
fun HomeScreen( navBackStackEntry: NavBackStackEntry) {
    val viewModel = hiltViewModel<HomeViewModel>()
    navBackStackEntry.arguments?.getString("token")?.let { Text(text = it) }


}