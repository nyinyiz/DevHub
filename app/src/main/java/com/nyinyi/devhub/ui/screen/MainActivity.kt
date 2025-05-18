package com.nyinyi.devhub.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nyinyi.devhub.ui.components.ErrorStatus
import com.nyinyi.devhub.ui.components.LoadingStatus
import com.nyinyi.devhub.ui.screen.userlist.UserListViewModel
import com.nyinyi.devhub.ui.theme.DevHubTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DevHubTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(
    viewModel: UserListViewModel = hiltViewModel(),
    name: String, modifier: Modifier = Modifier
) {
    val state = viewModel.state.collectAsState()

    Scaffold { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
        ) {
            item {
                if (state.value.isLoading || state.value.throwable != null) {
                    if (state.value.isLoading) {
                        LoadingStatus(
                            modifier = Modifier
                        )
                    } else if (state.value.throwable != null) {
                        ErrorStatus(
                            throwable = state.value.throwable,
                            onRetry = { viewModel.getData() },
                            modifier = Modifier
                                .padding(vertical = 4.dp)
                        )
                    }
                }
            }
            item {
                Text(
                    text = "Hello $name!",
                    modifier = modifier
                )
            }
            items(state.value.users.size) { index ->
                Text(text = state.value.users[index].name, modifier = Modifier.padding(16.dp))
            }
        }

    }

}