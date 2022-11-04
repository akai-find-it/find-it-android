package pl.org.akai.hackathon.di

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

	@Singleton
	@Provides
	fun provideMoshi(): Moshi =
		Moshi.Builder()
			.addLast(KotlinJsonAdapterFactory())
			.build()

	@Singleton
	@Provides
	fun provideOkHttpClient(): OkHttpClient =
		OkHttpClient.Builder()
			.build()

	@Singleton
	@Provides
	fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
		Retrofit.Builder()
			.baseUrl("10.0.0.13")
			.client(okHttpClient)
			.addConverterFactory(MoshiConverterFactory.create())
			.build()

//	@Singleton
//	@Provides
//	fun provideFindItApi(retrofit: Retrofit): FindItApi {
//		return retrofit.create(FindItApi::class.java)
//	}

}
