package com.hamidrezabashiri.signaling.ui.screens.lookup

import androidx.compose.runtime.Composable

@Composable
fun LookUpScreen(viewModel: LookUpViewModel, navigateToLogin: () -> Unit) {
    viewModel.lookup()
}