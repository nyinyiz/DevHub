package com.nyinyi.devhub.ui.screen.userDetail.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nyinyi.domain_model.UserDetail

@Composable
fun UserAvatar(userDetail: UserDetail) {
    AsyncImage(
        model = userDetail.avatarUrl,
        contentDescription = userDetail.name,
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(20.dp)),
        contentScale = ContentScale.Crop
    )
}