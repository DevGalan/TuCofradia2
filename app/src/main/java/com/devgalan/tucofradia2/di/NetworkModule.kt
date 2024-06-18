package com.devgalan.tucofradia2.di

import com.devgalan.tucofradia2.core.help.RetrofitHelper
import com.devgalan.tucofradia2.data.network.news.NewsApiClient
import com.devgalan.tucofradia2.data.network.server.ServerApiClient
import com.devgalan.tucofradia2.data.network.user.UserApiClient
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/api/")
//            .baseUrl("https://tucofradia-web-backend.onrender.com/api/")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val request = chain.request().newBuilder()
                            .addHeader("Authorization", RetrofitHelper.credentials)
                            .addHeader("Content-Type", "application/json")
                            .build()
                        chain.proceed(request)
                    }
                    .build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun provideUserApiClient(): UserApiClient {
        return provideRetrofit().create(UserApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideNewsApiClient(): NewsApiClient {
        return provideRetrofit().create(NewsApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideServersApiClient(): ServerApiClient {
        return provideRetrofit().create(ServerApiClient::class.java)
    }
}