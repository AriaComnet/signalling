package com.hamidrezabashiri.signaling.ui.screens.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry

@Composable
fun HomeScreen(viewModel: HomeViewModel, navBackStackEntry: NavBackStackEntry) {

    navBackStackEntry.arguments?.getString("token")?.let { Text(text = it) }


}