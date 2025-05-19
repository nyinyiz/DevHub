package com.nyinyi.devhub.ui.screen.userlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nyinyi.domain_model.User

@Composable
fun UserListItem(user: User, onUserClick: (User) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = { onUserClick(user) },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.5.dp
        ),
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                model = user.avatar,
                contentDescription = user.name,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = user.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
            )
            Icon(
                Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                contentDescription = "Go to details",
                tint = Color.Gray,
            )
        }
    }
}

@Preview(
    showBackground = true,
)
@Composable
fun UserListItemPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        UserListItem(user = User(1, "John Doe", "avatar_url"), onUserClick = {})
    }
}