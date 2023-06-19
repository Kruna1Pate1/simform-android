package com.krunal.demo.githubclient.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.krunal.demo.githubclient.data.remote.api.ApiService
import com.krunal.demo.githubclient.data.remote.api.AuthorizationService
import com.krunal.demo.githubclient.data.repository.ApiRepository
import com.krunal.demo.githubclient.data.repository.AuthorizationRepository
import com.krunal.demo.githubclient.util.GitHubAuthenticator
import com.krunal.demo.githubclient.util.GitHubUrls
import com.krunal.demo.helpers.PreferenceHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    private const val AUTH_RETROFIT = "AUTH_RETROFIT"
    private const val API_RETROFIT = "API_RETROFIT"
    private const val NETWORK_TIMEOUT: Long = 60 // Second

    @Provides
    fun providesAuthenticator(preferenceHelper: PreferenceHelper): Authenticator =
        GitHubAuthenticator(preferenceHelper)


    @Provides
    @Singleton
    fun providesGson(): Gson =
        GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()

    @Provides
    @Singleton
    fun provideOkHttpClient(authenticator: Authenticator): OkHttpClient =
        OkHttpClient.Builder().authenticator(authenticator)
            .readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS).build()

    @Provides
    @Singleton
    @Named(AUTH_RETROFIT)
    fun providesAuthRetrofit(gson: Gson, client: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(GitHubUrls.BASE_AUTH_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()

    @Provides
    @Singleton
    @Named(API_RETROFIT)
    fun providesApiRetrofit(gson: Gson, client: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(GitHubUrls.BASE_API_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()

    @Provides
    @Singleton
    fun providesAuthorizationService(@Named(AUTH_RETROFIT) retrofit: Retrofit): AuthorizationService =
        retrofit.create(AuthorizationService::class.java)

    @Provides
    @Singleton
    fun providesApiService(@Named(API_RETROFIT) retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun providesAuthorizationRepository(authorizationService: AuthorizationService): AuthorizationRepository =
        AuthorizationRepository(authorizationService)

    @Provides
    @Singleton
    fun providesApiRepository(apiService: ApiService): ApiRepository = ApiRepository(apiService)
}