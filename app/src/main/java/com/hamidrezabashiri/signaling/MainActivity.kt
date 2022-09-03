package com.hamidrezabashiri.signaling

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.messaging.FirebaseMessaging
import com.hamidrezabashiri.signaling.ui.navigation.SignalingNavGraph
import com.hamidrezabashiri.signaling.ui.theme.SignalingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseMessaging.getInstance().token
            .addOnSuccessListener(this) { it ->
                val newToken: String = it
                this.getSharedPreferences("_", Context.MODE_PRIVATE).edit()
                    .putString("fb", newToken)
                    .apply()
            }

        setContent {
            SignalingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreenContent(rememberNavController())
                }
            }
        }
    }
}


@Composable
private fun MainScreenContent(navController: NavHostController) {
    SignalingNavGraph(navController)
}

