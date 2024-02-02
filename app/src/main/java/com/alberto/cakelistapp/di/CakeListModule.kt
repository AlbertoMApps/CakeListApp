package com.alberto.cakelistapp.di

import android.util.Log
import com.alberto.cakelistapp.domain.CakeListRepositoryService
import com.alberto.cakelistapp.data.common.CAKE_LIST_API_BASE_URL
import com.alberto.cakelistapp.data.remote.api.CakeListApi
import com.alberto.cakelistapp.data.repository.CakeListRepositoryImplementation
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CakeListModule {

    @Provides
    @Singleton
    fun provideCakeListApi(
        client: OkHttpClient,
        gson: Gson
    ): CakeListApi =
        Retrofit.Builder()
            .baseUrl(CAKE_LIST_API_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(CakeListApi::class.java)

    @Provides
    @Singleton
    fun getGson(): Gson =
        GsonBuilder()
            .serializeNulls()
            .setLenient()
            .create()

    @Provides
    @Singleton
    fun getOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)

        return httpBuilder
            .protocols(mutableListOf(Protocol.HTTP_1_1))
            .build()
    }

    @Provides
    @Singleton
    fun getLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor { message -> Log.d("Logger", message) }
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    @Singleton
    fun provideCakeListRepositoryImplementation(
        api: CakeListApi
    ): CakeListRepositoryService =
        CakeListRepositoryImplementation(
            api
        )

}