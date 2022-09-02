package com.hamidrezabashiri.signaling.data.data_source.remote

import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

object SocketHandler {

    lateinit var mSocket: Socket

    @Synchronized
    fun setSocket(exteraHeader: HashMap<String, List<String>>) {
        try {
            mSocket = IO.socket(
                "http://46.148.32.196:3000",
                IO.Options.builder().setExtraHeaders(exteraHeader).build()
            )
        } catch (e: URISyntaxException) {

        }
    }

    @Synchronized
    fun getSocket(): Socket {
        return mSocket
    }

    @Synchronized
    fun establishConnection() {
        mSocket.connect()
    }

    @Synchronized
    fun closeConnection() {
        mSocket.disconnect()
    }
}