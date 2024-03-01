package ai.botstacks.sample.service

//import ai.botstacks.sample.R
//import ai.botstacks.sample.ui.MainActivity
//import ai.botstacks.sdk.BotStacksChat
//import android.app.NotificationChannel
//import android.app.NotificationManager
//import android.app.PendingIntent
//import android.content.Context
//import android.content.Intent
//import android.media.RingtoneManager
//import android.os.Build
//import android.util.Log
//import androidx.core.app.NotificationCompat
//import com.google.firebase.messaging.FirebaseMessagingService
//import com.google.firebase.messaging.RemoteMessage
//
//class MyFirebaseMessagingService : FirebaseMessagingService() {
//
//    override fun onMessageReceived(remoteMessage: RemoteMessage) {
//        // TODO(developer): Handle FCM messages here.
//        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
//        Log.d(TAG, "From: ${remoteMessage.from}")
//
//        // Check if message contains a data payload.
//        if (remoteMessage.data.isNotEmpty()) {
//            Log.d(TAG, "Message data payload: ${remoteMessage.data}")
//        }
//
//        // Check if message contains a notification payload.
//        remoteMessage.notification?.let {
//            Log.d(TAG, "Message Notification Body: ${it.body}")
//            sendNotification(it.body.orEmpty())
//        }
//
//        // Also if you intend on generating your own notifications as a result of a received FCM
//        // message, here is where that should be initiated. See sendNotification method below.
//    }
//
//    override fun onNewToken(token: String) {
//        Log.d(TAG, "Refreshed token: $token")
//
//        // If you want to send messages to this application instance or
//        // manage this apps subscriptions on the server side, send the
//        // FCM registration token to your app server.
//        sendRegistrationToServer(token)
//    }
//
//    private fun sendRegistrationToServer(token: String?) {
//        // TODO: Implement this method to send token to your app server.
//        token?.let { BotStacksChat.registerFCMToken(it) }
//    }
//
//    private fun sendNotification(messageBody: String) {
//        val intent = Intent(this, MainActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//        val requestCode = 0
//        val pendingIntent = PendingIntent.getActivity(
//            this,
//            requestCode,
//            intent,
//            PendingIntent.FLAG_IMMUTABLE,
//        )
//
//        val channelId = "fcm_default_channel"
//        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
//        val notificationBuilder = NotificationCompat.Builder(this, channelId)
//            .setSmallIcon(R.mipmap.ic_launcher)
//            .setContentTitle("FCM Message")
//            .setContentText(messageBody)
//            .setAutoCancel(true)
//            .setSound(defaultSoundUri)
//            .setContentIntent(pendingIntent)
//
//        val notificationManager =
//            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        // Since android Oreo notification channel is needed.
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channel = NotificationChannel(
//                channelId,
//                "Channel human readable title",
//                NotificationManager.IMPORTANCE_DEFAULT,
//            )
//            notificationManager.createNotificationChannel(channel)
//        }
//
//        val notificationId = 0
//        notificationManager.notify(notificationId, notificationBuilder.build())
//    }
//
//    companion object {
//        private const val TAG = "MyFirebaseMsgService"
//    }
//}