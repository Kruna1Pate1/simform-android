package com.krunal.demo.githubclient.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.krunal.demo.githubclient.data.remote.api.AuthorizationService
import com.krunal.demo.githubclient.data.remote.api.IssueService
import com.krunal.demo.githubclient.data.remote.api.NotificationService
import com.krunal.demo.githubclient.data.remote.api.RepoService
import com.krunal.demo.githubclient.data.remote.api.UserService
import com.krunal.demo.githubclient.data.repository.AuthorizationRepository
import com.krunal.demo.githubclient.data.repository.HomeRepository
import com.krunal.demo.githubclient.data.repository.IssueRepository
import com.krunal.demo.githubclient.data.repository.NotificationRepository
import com.krunal.demo.githubclient.data.repository.RepoRepository
import com.krunal.demo.githubclient.data.repository.UserRepository
import com.krunal.demo.githubclient.util.GitHubAuthenticator
import com.krunal.demo.githubclient.util.GitHubUrls
import com.krunal.demo.helpers.PreferenceHelper
import com.krunal.demo.utils.PreferenceKeys
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
        OkHttpClient.Builder().authenticator(authenticator).addInterceptor {
            val request = it.request().newBuilder().addHeader(
                "Authorization",
                "Bearer " + PreferenceHelper.getString(PreferenceKeys.AUTHORIZATION_TOKEN, "")
            ).build()
            it.proceed(request)
        }.readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
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
    fun providesUserService(@Named(API_RETROFIT) retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)

    @Provides
    @Singleton
    fun providesNotificationService(@Named(API_RETROFIT) retrofit: Retrofit): NotificationService =
        retrofit.create(NotificationService::class.java)

    @Provides
    @Singleton
    fun providesRepoService(@Named(API_RETROFIT) retrofit: Retrofit): RepoService =
        retrofit.create(RepoService::class.java)

    @Provides
    @Singleton
    fun providesIssueService(@Named(API_RETROFIT) retrofit: Retrofit): IssueService =
        retrofit.create(IssueService::class.java)

    @Provides
    @Singleton
    fun providesAuthorizationRepository(authorizationService: AuthorizationService): AuthorizationRepository =
        AuthorizationRepository(authorizationService)

    @Provides
    @Singleton
    fun providesUserRepository(userService: UserService): UserRepository =
        UserRepository(userService)

    @Provides
    @Singleton
    fun providesHomeRepository(): HomeRepository = HomeRepository()

    @Provides
    @Singleton
    fun providesNotificationRepository(notificationService: NotificationService): NotificationRepository =
        NotificationRepository(notificationService)

    @Provides
    @Singleton
    fun providesRepoRepository(repoService: RepoService): RepoRepository =
        RepoRepository(repoService)

    @Provides
    @Singleton
    fun providesIssueRepository(issueService: IssueService): IssueRepository =
        IssueRepository(issueService)
}