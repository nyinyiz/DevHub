package com.nyinyi.devhub.ui.screen.userDetail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nyinyi.domain_model.UserDetail

@Composable
fun UserInfo(
    userDetail: UserDetail,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = userDetail.login,
            style = MaterialTheme.typography.titleMedium,
        )
        Text(
            text = userDetail.name,
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.secondary
            ),
        )
        // show location with icon if not empty
        if (userDetail.location.isNotEmpty()) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location",
                    tint = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = userDetail.location,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.secondary
                    ),
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FollowButton(
                count = userDetail.followers,
                label = "Followers",
                modifier = Modifier.weight(1f)
            )
            FollowButton(
                count = userDetail.following,
                label = "Following",
                modifier = Modifier.weight(1f)
            )
        }
    }
}