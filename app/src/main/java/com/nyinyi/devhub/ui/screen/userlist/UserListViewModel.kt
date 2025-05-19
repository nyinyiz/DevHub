package com.nyinyi.devhub.ui.screen.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyinyi.common.exceptions.DisconnectException
import com.nyinyi.common.utils.ConnectionObserver
import com.nyinyi.devhub.provider.DispatcherProvider
import com.nyinyi.domain.usecase.GetSearchUserListUseCase
import com.nyinyi.domain.usecase.GetUserListUseCase
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
class UserListViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase,
    private val getSearchUserListUseCase: GetSearchUserListUseCase,
    private val connectionObserver: ConnectionObserver,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _state: MutableStateFlow<UserListUiState> = MutableStateFlow(UserListUiState())
    val state = _state.asStateFlow()

    init {
        with(connectionObserver) {
            onConnected = {
                getData()
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

    fun getData() {
        getUserListUseCase()
            .onStart {
                Timber.d("Fetching user list...")
                _state.update { it.copy(isLoading = true, throwable = null) }
            }
            .onEach { users ->
                users.forEach {
                    Timber.d("User: $it")
                }
                _state.update { it.copy(isLoading = false, users = users, throwable = null) }
            }
            .flowOn(dispatcherProvider.io())
            .catch { e ->
                Timber.e(e, "Error fetching user list")
                _state.update { it.copy(isLoading = false, throwable = e) }
            }
            .launchIn(viewModelScope)
    }

    fun searchUsers(query: String) {
        getSearchUserListUseCase(query)
            .onStart {
                Timber.d("Fetching user list...")
                _state.update { it.copy(isLoading = true, throwable = null) }
            }
            .onEach { users ->
                users.forEach {
                    Timber.d("User: $it")
                }
                _state.update { it.copy(isLoading = false, users = users, throwable = null) }
            }
            .flowOn(dispatcherProvider.io())
            .catch { e ->
                Timber.e(e, "Error fetching user list")
                _state.update { it.copy(isLoading = false, throwable = e) }
            }
            .launchIn(viewModelScope)
    }
}
