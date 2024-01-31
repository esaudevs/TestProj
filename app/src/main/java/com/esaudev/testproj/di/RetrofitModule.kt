package com.esaudev.testproj.di

import com.esaudev.testproj.data.remote.ImageApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    const val IMAGE_SERVER_BASE_URL = "https://images.dog.ceo/"

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    @Singleton
    fun providePokemonApi(
        client: OkHttpClient
    ): ImageApi {
        return Retrofit.Builder()
            .baseUrl(IMAGE_SERVER_BASE_URL)
            .client(client)
            .build()
            .create()
    }
}