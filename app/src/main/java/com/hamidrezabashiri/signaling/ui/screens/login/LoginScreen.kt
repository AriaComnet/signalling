package com.hamidrezabashiri.signaling.ui.screens.login

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import com.hamidrezabashiri.signaling.utils.NetworkResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navBackStack: NavBackStackEntry,
    navigateToHome: (token: String) -> Unit,
) {
    val viewModel = hiltViewModel<LoginViewModel>()
    val response by viewModel.response
    val code = viewModel.code.collectAsState()
    var isNavigated by rememberSaveable { mutableStateOf(false) }


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        TextField(
            value = code.value,
            onValueChange = viewModel::setCode,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
        Button(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(), onClick = {
            navBackStack.arguments?.getString("temp_token")?.let {
                viewModel.onLoginButtonClick(
                    navBackStack.arguments?.getString("phone")!!,
                    it
                )
            }

        }) {
            when (response) {
                is NetworkResult.Success<*> -> {
                    LaunchedEffect(key1 = Unit) {
                        if (!isNavigated) {
                            response.data?.data?.token?.let { navigateToHome(it) }
                            isNavigated = true
                        }
                    }
                    Text(text = "Send Code")

                }
                is NetworkResult.Error<*> -> {
                    Log.i("TAG", "error: " + response.message)
                    Text(text = "Error")

                }
                is NetworkResult.Loading<*> -> {
                    CircularProgressIndicator(color = Color.White, strokeWidth = 2.dp)
                }
                else -> {
                    Text(text = "Send Code")
                }
            }
        }

    }
}