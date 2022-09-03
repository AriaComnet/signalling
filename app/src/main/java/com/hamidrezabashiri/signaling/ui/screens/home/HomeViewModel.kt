package com.hamidrezabashiri.signaling.ui.screens.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hamidrezabashiri.signaling.data.data_source.remote.SocketService
import com.hamidrezabashiri.signaling.data.repository.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.socket.client.Socket
import javax.inject.Inject
import kotlin.collections.HashMap
import kotlin.collections.List
import kotlin.collections.listOf
import kotlin.collections.set

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: AuthenticationRepository,
    private val socketService: SocketService
) : ViewModel() {


    fun establishConnection(token: String, pushToken: String) {
        val map: HashMap<String, List<String>> = HashMap()
        map["Authorization"] = listOf("Bearer $token")
        map["push_token"] = listOf(pushToken)

        socketService.setSocket(map)
        socketService.establishConnection()
        val socket = socketService.getSocket()

        socket.on("connected"){
            Log.i("TAG", "sadasdasdasdasdas: "+socketService.getSocket().isActive)
        }
        socket.on("test"){
            Log.i("TAG", "establishConnection: test$it")
        }
        socket.on("new"){
            Log.i("TAG", "rec: test$it")
        }

    }
}