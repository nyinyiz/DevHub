package com.nyinyi.devhub.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nyinyi.devhub.R

@Composable
fun LoadingStatus(modifier: Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.text_refreshing_data),
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 4.dp),
            style = MaterialTheme.typography.bodyMedium
        )
        CircularProgressIndicator(modifier = Modifier.size(20.dp))
    }
}