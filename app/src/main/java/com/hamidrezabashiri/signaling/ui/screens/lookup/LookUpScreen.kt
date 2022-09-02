package com.hamidrezabashiri.signaling.ui.screens.lookup

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hamidrezabashiri.signaling.utils.NetworkResult

@SuppressLint("ShowToast")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LookUpScreen(
    viewModel: LookUpViewModel,
    navigateToLogin: (phone: String, tempToken: String) -> Unit
) {

    val response by viewModel.response
    val phoneNumber by viewModel.phoneNumber.collectAsState()
    var isNavigated by rememberSaveable { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        TextField(
            value = phoneNumber,
            onValueChange = viewModel::setPhoneNumber,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
        Button(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(), onClick = {
            viewModel.onSendButtonClick()
        }) {
            when (response) {
                is NetworkResult.Success<*> -> {
                    LaunchedEffect(key1 = Unit) {
                        if (!isNavigated) {
                            navigateToLogin(
                                phoneNumber,
                                response.data?.data?.temporaryToken.toString()
                            )
                            isNavigated = true
                        }
                    }
                    Text(text = "Send")

                }
                is NetworkResult.Error<*> -> {
                    Log.i("TAG", "eeeee: " + response.message)
                    Text(text = "Error")

                }
                is NetworkResult.Loading<*> -> {
                    CircularProgressIndicator(color = Color.White, strokeWidth = 2.dp)
                }
                else -> {
                    Text(text = "Send")
                }
            }
        }

    }


}
