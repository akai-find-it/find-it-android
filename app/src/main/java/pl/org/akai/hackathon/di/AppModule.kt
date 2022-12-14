package pl.org.akai.hackathon.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.org.akai.hackathon.data.AppDb
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

	@Singleton
	@Provides
	fun provideContext(app: Application): Context = app

	@Singleton
	@Provides
	fun provideAppDb(context: Context) =
		Room.databaseBuilder(context, AppDb::class.java, "data.db")
			.build()
}
