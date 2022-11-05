package pl.org.akai.hackathon

import android.app.Application
import com.google.android.material.color.DynamicColors
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import pl.org.akai.hackathon.data.api.ApiService
import pl.org.akai.hackathon.data.model.TokenRequest
import pl.org.akai.hackathon.di.NetworkModule
import javax.inject.Inject

@HiltAndroidApp
class FindItApp : Application(), CoroutineScope {

	override val coroutineContext = Dispatchers.IO + Job()

	override fun onCreate() {
		super.onCreate()
		DynamicColors.applyToActivitiesIfAvailable(this)
		FirebaseMessaging.getInstance().token
			.addOnCompleteListener {
				if (!it.isSuccessful)
					return@addOnCompleteListener
				val token = it.result
				sendPushToken(token)
			}
	}

	fun sendPushToken(token: String) = launch {
		try {
			val nm = NetworkModule()
			val api = nm.provideFindItApi(nm.provideRetrofit(nm.provideOkHttpClient(this@FindItApp), nm.provideMoshi()))
			api.sendPushToken(TokenRequest(token))
		} catch (e: Exception) {
			e.printStackTrace()
		}
	}
}

