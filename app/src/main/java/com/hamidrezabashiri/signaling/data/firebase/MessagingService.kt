package com.hamidrezabashiri.signaling.data.firebase

import android.content.Context
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import javax.inject.Singleton

@Singleton
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
        getSharedPreferences("_", MODE_PRIVATE).edit().putString("fb", token).apply();
    }

    fun getToken(context: Context): String? {
        return context.getSharedPreferences("_", MODE_PRIVATE).getString("fb", "empty")
    }

}
