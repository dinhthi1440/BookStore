package com.example.bookstore.data.service

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.bookstore.R
import com.example.bookstore.extensions.generateRandomNotifiID
import com.example.bookstore.extensions.getCurrentDateTime
import com.example.bookstore.extensions.getUserID
import com.example.bookstore.ui.main.MainActivity
import com.example.bookstore.util.Constant
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.koin.android.ext.android.get
import java.util.Calendar
import kotlin.random.Random

class FirebaseCloudMessage:FirebaseMessagingService() {
    private val db = Firebase.firestore
    private val sharedPreferences by lazy { get<SharedPreferences>() }
    override fun onMessageReceived(message: RemoteMessage) {
        if(message.notification != null){
            sendNotification(message.notification!!.title!!, message.notification!!.body!!)
            val notificationID = Random.generateRandomNotifiID()
            db.collection("user").document("notifications").collection(sharedPreferences.getUserID()!!)
                .document(notificationID).set(
                    mapOf(
                        "userID" to sharedPreferences.getUserID(),
                        "notificationID" to notificationID,
                        "title" to message.notification!!.title,
                        "description" to message.notification!!.body,
                        "time" to Calendar.getInstance().getCurrentDateTime(),
                        "icon" to "R.id.img_avt_user"
                    )
                )
        }
    }

    @SuppressLint("RemoteViewLayout")
    fun getRemoteView(title: String, description: String): RemoteViews {
        val remoteView = RemoteViews("com.example.bookstore", R.layout.item_notification)
        remoteView.setTextViewText(R.id.txtv_title, title)
        remoteView.setTextViewText(R.id.txtv_description, description)
        remoteView.setImageViewResource(R.id.img_notification, R.drawable.clock_icon_16)
        return  remoteView
    }
    @SuppressLint("UnspecifiedImmutableFlag")
    private fun sendNotification(title: String, description_notif: String){
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT)
        var build: NotificationCompat.Builder = NotificationCompat.Builder(applicationContext, Constant.CHANNEL_ID)
            .setSmallIcon(R.drawable.clock_icon_16)
            .setAutoCancel(true)
            .setOnlyAlertOnce(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000))
            .setContentIntent(pendingIntent)
        build = build.setContent(getRemoteView(title, description_notif))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(
                Constant.CHANNEL_ID,
                Constant.CHANNEL_NAME,
                importance
            ).apply {
                description = description_notif
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
            notificationManager.notify(0, build.build())
        }
    }

}