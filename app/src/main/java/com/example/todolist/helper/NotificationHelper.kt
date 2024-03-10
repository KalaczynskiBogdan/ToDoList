package com.example.todolist.helper

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.todolist.MainActivity
import com.example.todolist.R

class NotificationHelper(private val context: Context) {
    companion object{
        const val NOTIFICATION_ID = 1
        private const val CHANNEL_ID = "ChannelId"
        private const val CHANNEL_NAME = "ChannelName"
    }
    private val contentIntent by lazy {
        PendingIntent.getActivity(
            context,
            0,
            Intent(context, MainActivity::class.java),
            PendingIntent.FLAG_IMMUTABLE
        )
    }
    private val notificationBuilder: NotificationCompat.Builder by lazy {
        NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("ToDoList")
            .setSound(null)
            .setContentIntent(contentIntent)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setAutoCancel(true)
    }

    private val notificationManager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE)as NotificationManager
    }
    private fun createChannel() =
        NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_LOW
        )

    fun getNotification(): Notification {
        notificationManager.createNotificationChannel(createChannel())
        return notificationBuilder.build()
    }

    fun updateNotification(text:String?){
        text?.let {
            notificationBuilder.setContentText(it)
        }
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
    }
}