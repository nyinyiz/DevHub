package com.nyinyi.data.provider

import com.nyinyi.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object OkHttpProvider {
    private val API_TIMEOUT: Long
        get() = if (BuildConfig.DEBUG) 30L else 10L

    class HeaderInterceptor(
        private val authToken: String
    ) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder()
                .header("Authorization", "Bearer $authToken")
                .build()
            return chain.proceed(request)
        }
    }

    fun getOkHttpProvider(
        authToken: String
    ): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(API_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(API_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(API_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(
                HeaderInterceptor(
                    authToken
                )
            )
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                }
            )
            .build()
        return okHttpClient
    }
}