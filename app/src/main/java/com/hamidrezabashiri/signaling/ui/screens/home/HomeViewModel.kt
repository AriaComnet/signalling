package com.hamidrezabashiri.signaling.ui.screens.home

import androidx.lifecycle.ViewModel
import com.hamidrezabashiri.signaling.data.repository.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(repository: AuthenticationRepository) : ViewModel() {
}