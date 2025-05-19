package com.nyinyi.devhub.ui.screen.userDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyinyi.common.exceptions.DisconnectException
import com.nyinyi.common.utils.ConnectionObserver
import com.nyinyi.devhub.provider.DispatcherProvider
import com.nyinyi.domain.usecase.GetUserDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getUserDetailUseCase: GetUserDetailUseCase,
    private val connectionObserver: ConnectionObserver,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {
    private val _state: MutableStateFlow<UserDetailState> = MutableStateFlow(UserDetailState())
    val state = _state.asStateFlow()

    fun checkConnectionAndGetData(userName: String) {
        with(connectionObserver) {
            onConnected = {
                getUserDetail(userName)
            }
            onDisconnected = {
                _state.update { it.copy(isLoading = false, throwable = DisconnectException) }
            }
            startObserving()
        }
    }

    override fun onCleared() {
        super.onCleared()
        connectionObserver.stopObserving()
    }

    private fun getUserDetail(userName: String) {
        getUserDetailUseCase(
            userName = userName
        )
            .onStart {
                Timber.d("Fetching user list...")
                _state.update { it.copy(isLoading = true, throwable = null) }
            }
            .onEach { userDetail ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        userDetail = userDetail,
                        throwable = null
                    )
                }
            }
            .flowOn(dispatcherProvider.io())
            .catch { e ->
                Timber.e(e, "Error fetching user list")
                _state.update { it.copy(isLoading = false, throwable = e) }
            }
            .launchIn(viewModelScope)
    }
}