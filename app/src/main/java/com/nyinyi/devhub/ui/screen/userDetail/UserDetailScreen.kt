package com.nyinyi.devhub.ui.screen.userDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nyinyi.devhub.ui.components.ErrorStatus
import com.nyinyi.devhub.ui.components.LoadingStatus
import com.nyinyi.devhub.ui.screen.userDetail.components.ProfileTopBar
import com.nyinyi.devhub.ui.screen.userDetail.components.RepositoryItem
import com.nyinyi.devhub.ui.screen.userDetail.components.ShimmerUserDetailCard
import com.nyinyi.devhub.ui.screen.userDetail.components.UserDetailCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(
    viewModel: UserDetailViewModel = hiltViewModel(),
    onBack: () -> Unit,
    onClickWebView: (String) -> Unit,
    userName: String
) {
    val state = viewModel.state.collectAsState()
    val userDetail = state.value.userDetail
    val isLoading = state.value.isLoading
    val throwable = state.value.throwable
    val repo = state.value.userRepos
    val isRepoLoading = state.value.isRepoLoading

    LaunchedEffect(userName) {
        viewModel.loadUserData(userName)
    }

    Scaffold(
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFFE0E7FF),
                    Color(0xFFF0F4F8)
                )
            )
        ),
        topBar = { ProfileTopBar(userName, onBack) },
        containerColor = Color.Transparent
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = Color.Transparent
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.Start
            ) {
                when {
                    isLoading -> ShimmerUserDetailCard()

                    throwable != null -> ErrorStatus(
                        throwable = throwable,
                        onRetry = { viewModel.loadUserData(userName) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )

                    userDetail != null -> UserDetailCard(
                        userDetail = userDetail
                    )
                }
                Text(
                    text = "Repositories",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(
                        start = 8.dp,
                        top = 8.dp,
                        bottom = 8.dp
                    )
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    when {
                        isRepoLoading -> {
                            item {
                                LoadingStatus(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                )
                            }
                        }

                        repo.isNotEmpty() -> {
                            items(repo.size) { index ->
                                RepositoryItem(
                                    repository = repo[index],
                                    onClickWebView = { url ->
                                        onClickWebView(url)
                                    }
                                )
                            }
                        }

                        repo.isEmpty() -> {
                            item {
                                Text(
                                    text = "No repositories found",
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(
                                        start = 8.dp
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
