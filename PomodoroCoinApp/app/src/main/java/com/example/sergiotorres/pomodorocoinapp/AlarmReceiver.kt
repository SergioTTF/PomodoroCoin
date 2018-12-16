package com.example.sergiotorres.pomodorocoinapp

import android.app.Notification
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Vibrator

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        TODO("AlarmReceiver.onReceive() is not implemented")
        val vibrator:Vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(2000)
        Notification.CATEGORY_ALARM
    }
}
