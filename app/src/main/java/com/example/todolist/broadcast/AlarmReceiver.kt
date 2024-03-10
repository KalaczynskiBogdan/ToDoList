package com.example.todolist.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.todolist.helper.NotificationHelper

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val text = intent?.getStringExtra("alarm")

        if (context != null){
            val notificationHelper = NotificationHelper(context)
            notificationHelper.getNotification()

            notificationHelper.updateNotification(text)

        }
    }
}