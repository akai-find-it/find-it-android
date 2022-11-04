package pl.org.akai.hackathon

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FindItApp : Application() {

	override fun onCreate() {
		super.onCreate()
	}
}

