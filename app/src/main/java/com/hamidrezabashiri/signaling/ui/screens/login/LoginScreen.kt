package com.hamidrezabashiri.signaling.ui.screens.login

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(viewModel: LoginViewModel, navBackStack: NavBackStackEntry) {

    val tempLoginToken by viewModel.tempToken.observeAsState()
    val code = viewModel.code.collectAsState()

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
                viewModel.login(
                    navBackStack.arguments?.getString("phone")!!,
                    it
                )
            }
        }) {
            Text(text = "Send Code")
        }
        if (tempLoginToken?.data?.token != null) {
            Log.i("TAG", "looo: " + tempLoginToken?.data?.token)
            Log.i("TAG", "looo: " + tempLoginToken?.message)
        }
    }
}