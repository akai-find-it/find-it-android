package pl.org.akai.hackathon

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import pl.org.akai.hackathon.ui.main.MainActivity

class MyFirebaseMessagingService : FirebaseMessagingService() {
	companion object {
		private const val TAG = "FCM"
	}

	override fun onMessageReceived(remoteMessage: RemoteMessage) {
		Log.d(TAG, "From: ${remoteMessage.from}")

		// Check if message contains a data payload.
		if (remoteMessage.data.isNotEmpty()) {
			Log.d(TAG, "Message data payload: ${remoteMessage.data}")
		}
	}

	override fun onNewToken(token: String) {
		Log.d(TAG, "Refreshed token: $token")
	}

	private fun sendNotification(messageBody: String) {
		val intent = Intent(this, MainActivity::class.java)
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
		val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
			PendingIntent.FLAG_IMMUTABLE)

		val channelId = "fcm_default_channel"
		val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
		val notificationBuilder = NotificationCompat.Builder(this, channelId)
			.setContentTitle("FCM Message")
			.setContentText(messageBody)
			.setAutoCancel(true)
			.setSound(defaultSoundUri)
			.setContentIntent(pendingIntent)

		val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

		// Since android Oreo notification channel is needed.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			val channel = NotificationChannel(channelId,
				"Channel human readable title",
				NotificationManager.IMPORTANCE_DEFAULT)
			notificationManager.createNotificationChannel(channel)
		}

		notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
	}
}
