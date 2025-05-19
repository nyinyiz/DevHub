package com.nyinyi.devhub.ui.screen.userDetail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nyinyi.devhub.ui.theme.SecondaryText
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

        if (userDetail.location.isNotEmpty()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = "Location",
                    tint = SecondaryText,
                )
                Text(
                    text = userDetail.location,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = SecondaryText,
                    ),
                )
            }
        }

        if (userDetail.email.isNotEmpty()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = Icons.Outlined.Email,
                    contentDescription = "Email",
                    tint = SecondaryText,
                )
                Text(
                    text = userDetail.email,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = SecondaryText,
                    ),
                )
            }
        }
    }
}