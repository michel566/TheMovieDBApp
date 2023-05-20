package com.example.themoviedbapp.framework.di

import com.example.themoviedbapp.BuildConfig
import com.example.themoviedbapp.framework.network.api.TMDBApi
import com.example.themoviedbapp.framework.network.interceptor.AuthorizationInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val TIMEOUT_SECONDS = 15L

    @Provides
    fun provideLoginInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            setLevel(
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            )
        }


    @Provides
    fun provideAuthorizationInterceptor(): AuthorizationInterceptor =
        AuthorizationInterceptor(BuildConfig.PRIVATE_KEY)

    @Provides
    fun provideOkHttpClient(
        loginInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthorizationInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(loginInterceptor)
            .addInterceptor(authInterceptor)
            .build()

    @Provides
    fun provideGson(): Gson =
        GsonBuilder().setLenient().create()

    @Provides
    fun provideConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)

    @Provides
    fun provideRetrofit(
        httpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): TMDBApi = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(httpClient)
        .addConverterFactory(converterFactory)
        .build()
        .create(TMDBApi::class.java)

}