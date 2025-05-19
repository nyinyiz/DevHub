package com.nyinyi.devhub.ui.screen.userDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyinyi.common.exceptions.DisconnectException
import com.nyinyi.common.utils.ConnectionObserver
import com.nyinyi.devhub.provider.DispatcherProvider
import com.nyinyi.domain.usecase.GetUserDetailUseCase
import com.nyinyi.domain.usecase.GetUserRepoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getUserDetailUseCase: GetUserDetailUseCase,
    private val getUserRepoUseCase: GetUserRepoUseCase,
    private val connectionObserver: ConnectionObserver,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {
    private val _state = MutableStateFlow(UserDetailState())
    val state = _state.asStateFlow()

    fun loadUserData(userName: String) {
        setupConnectionObserver(userName)
    }

    private fun setupConnectionObserver(userName: String) {
        connectionObserver.apply {
            onConnected = { fetchUserData(userName) }
            onDisconnected = {
                _state.update { it.copy(isLoading = false, throwable = DisconnectException) }
            }
            startObserving()
        }
    }

    private fun fetchUserData(userName: String) {
        getUserDetail(userName)
        getUserRepositories(userName)
    }

    override fun onCleared() {
        super.onCleared()
        connectionObserver.stopObserving()
    }

    private fun getUserDetail(userName: String) {
        getUserDetailUseCase(userName = userName)
            .onStart {
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
            .catch { error ->
                _state.update { it.copy(isLoading = false, throwable = error) }
            }
            .launchIn(viewModelScope)
    }

    private fun getUserRepositories(userName: String) {
        getUserRepoUseCase(userName = userName)
            .onStart {
                _state.update { it.copy(isRepoLoading = true) }
            }
            .onEach { repositories ->
                _state.update {
                    it.copy(
                        isRepoLoading = false,
                        userRepos = repositories.filter { it.fork != true }
                            .sortedByDescending { it.updatedAt }
                    )
                }
            }
            .flowOn(dispatcherProvider.io())
            .catch { error ->
                _state.update { it.copy(isRepoLoading = false, throwable = error) }
            }
            .launchIn(viewModelScope)
    }
}
