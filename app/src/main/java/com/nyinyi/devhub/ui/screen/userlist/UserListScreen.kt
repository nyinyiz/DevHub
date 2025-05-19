package com.nyinyi.devhub.ui.screen.userlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.nyinyi.devhub.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    viewModel: UserListViewModel = hiltViewModel(),
    onUserClick: (String) -> Unit = {}
) {
    val state = viewModel.state.collectAsState()

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
                        modifier = Modifier.padding(8.dp)
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
                TextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    textStyle = TextStyle(fontSize = 16.sp),
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = "Search",
                            tint = Color.Gray
                        )
                    },
                    placeholder = { Text("Search GitHub Users", color = Color.Gray) },
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
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