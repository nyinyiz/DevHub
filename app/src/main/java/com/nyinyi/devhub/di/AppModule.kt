package com.nyinyi.devhub.di

import com.nyinyi.common.utils.ConnectionObserver
import com.nyinyi.common.utils.ConnectionObserverImpl
import com.nyinyi.devhub.provider.DefaultDispatcherProvider
import com.nyinyi.devhub.provider.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindConnectionObserver(
        connectionObserver: ConnectionObserverImpl
    ): ConnectionObserver

    @Binds
    @Singleton
    abstract fun bindDispatcherProvider(
        dispatcherProvider: DefaultDispatcherProvider
    ): DispatcherProvider
}