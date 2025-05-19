package com.nyinyi.devhub.ui.screen.userDetail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nyinyi.domain_model.UserDetail

@Composable
fun UserDetailCard(
    userDetail: UserDetail,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            UserAvatar(userDetail)
            Spacer(modifier = Modifier.width(12.dp))
            UserInfo(
                userDetail = userDetail,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Preview
@Composable
fun UserDetailCardPreview() {
    UserDetailCard(
        userDetail = UserDetail(
            login = " login",
            id = 1,
            nodeId = " nodeId",
            avatarUrl = "",
            gravatarId = " gravatarId",
            url = " url",
            htmlUrl = " htmlUrl",
            followersUrl = " followersUrl",
            followingUrl = " followingUrl",
            gistsUrl = " gistsUrl",
            starredUrl = " starredUrl",
            subscriptionsUrl = " subscriptionsUrl",
            organizationsUrl = " organizationsUrl",
            reposUrl = " reposUrl",
            eventsUrl = " eventsUrl",
            receivedEventsUrl = " receivedEventsUrl",
            type = " type",
            userViewType = " userViewType",
            siteAdmin = true,
            name = " name",
            company = " company",
            blog = " blog",
            location = " location",
            email = " email",
            hireable = true,
            bio = " bio",
            twitterUsername = " twitterUsername",
            publicRepos = 1,
            publicGists = 2,
            followers = 2,
            following = 3,
            createdAt = " createdAt",
            updatedAt = " updatedAt",
        )
    )
}