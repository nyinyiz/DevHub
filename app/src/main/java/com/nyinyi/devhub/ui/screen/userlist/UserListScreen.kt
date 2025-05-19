package com.nyinyi.devhub.ui.screen.userlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.nyinyi.devhub.R
import com.nyinyi.devhub.ui.components.ErrorStatus
import com.nyinyi.devhub.ui.components.LoadingStatus
import com.nyinyi.devhub.ui.components.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    viewModel: UserListViewModel = hiltViewModel(),
    onUserClick: (String) -> Unit = {}
) {
    val state = viewModel.state.collectAsState()
    val listState = rememberLazyListState()
    var searchText by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
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
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "GitHub Users",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                },
                actions = {
                    AsyncImage(
                        model = R.mipmap.ic_launcher_round,
                        contentDescription = stringResource(R.string.app_name),
                        modifier = Modifier
                            .size(50.dp)
                            .padding(8.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        containerColor = Color.Transparent
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = Color.Transparent,
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SearchBar(
                    value = searchText,
                    onValueChange = { searchText = it },
                    modifier = Modifier
                        .padding(bottom = 16.dp),
                    placeholder = "Search GitHub Users"
                )

                if (state.value.isLoading || state.value.throwable != null) {
                    if (state.value.isLoading) {
                        LoadingStatus(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                        )
                    } else if (state.value.throwable != null) {
                        ErrorStatus(
                            throwable = state.value.throwable,
                            onRetry = { viewModel.getData() },
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(vertical = 4.dp)
                        )
                    }
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    state = listState
                ) {
                    items(state.value.users.size) { index ->
                        UserListItem(
                            user = state.value.users[index], onUserClick =
                                { user ->
                                    onUserClick(user.name)
                                })
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun UserListScreenPreview() {
    UserListScreen()
}