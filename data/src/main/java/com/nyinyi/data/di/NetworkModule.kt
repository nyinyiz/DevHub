package com.nyinyi.data.di

import android.content.Context
import com.nyinyi.data.network.service.GitHubApiService
import com.nyinyi.data.provider.OkHttpProvider
import com.nyinyi.data.provider.RetrofitProvider
import com.nyinyi.data.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideRetrofit(
        okHttpClient: okhttp3.OkHttpClient
    ): retrofit2.Retrofit {
        return RetrofitProvider.getRetrofitProvider(
            okHttpClient,
        )
    }

    @Provides
    fun provideOkHttpClient(
        @ApplicationContext context: Context
    ): okhttp3.OkHttpClient {
        return OkHttpProvider.getOkHttpProvider(
            BuildConfig.AUTH_TOKEN,
        )
    }

    @Provides
    @Singleton
    fun provideGitHubApiService(retrofit: Retrofit): GitHubApiService {
        return retrofit.create(GitHubApiService::class.java)
    }
}