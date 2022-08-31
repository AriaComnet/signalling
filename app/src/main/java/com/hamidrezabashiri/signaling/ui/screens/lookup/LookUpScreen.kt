package com.hamidrezabashiri.signaling.ui.screens.lookup

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LookUpScreen(viewModel: LookUpViewModel, navController: NavHostController) {

    val tempLookUpToken by viewModel.tempToken.observeAsState()
    val phoneNumber = viewModel.phoneNumber.collectAsState()
    var isNavigated by rememberSaveable{ mutableStateOf(false)}

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        TextField(
            value = phoneNumber.value,
            onValueChange = viewModel::setPhoneNumber,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
        Button(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(), onClick = {
            viewModel.lookup()
        }) {
            Text(text = "Send")
        }

    }
    if (!isNavigated){
        if (tempLookUpToken?.data?.temporaryToken != null) {
            navController.navigate(
                "login/" + phoneNumber.value + "/" + tempLookUpToken?.data?.temporaryToken
            )
            isNavigated=true
        }
    }
}