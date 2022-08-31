package com.hamidrezabashiri.signaling.data.firebase

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MessagingService() : FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
    }

    override fun onDeletedMessages() {
        super.onDeletedMessages()
    }

    override fun onMessageSent(msgId: String) {
        super.onMessageSent(msgId)
    }

    override fun onSendError(msgId: String, exception: Exception) {
        super.onSendError(msgId, exception)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.i("TAG", "onNewToken: "+token)

    }


}
