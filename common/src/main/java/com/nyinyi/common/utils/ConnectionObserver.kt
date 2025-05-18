package com.nyinyi.common.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.core.content.getSystemService
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface ConnectionObserver {
    var onConnected: () -> Unit
    var onDisconnected: () -> Unit

    fun startObserving()
    fun stopObserving()
}

class ConnectionObserverImpl @Inject constructor(
    @ApplicationContext context: Context
) : ConnectionObserver {

    private val connectivityManager = context.getSystemService<ConnectivityManager>()

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            onConnected()
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            onDisconnected()
        }
    }

    override var onConnected: () -> Unit = {}
    override var onDisconnected: () -> Unit = {}

    override fun startObserving() {

        val networkRequest = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()
        connectivityManager?.registerNetworkCallback(networkRequest, networkCallback)
    }

    override fun stopObserving() {
        connectivityManager?.unregisterNetworkCallback(networkCallback)
    }

}