package com.nyinyi.devhub.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.nyinyi.common.exceptions.DisconnectException
import com.nyinyi.devhub.R

@Composable
fun ErrorStatus(
    throwable: Throwable?,
    modifier: Modifier,
    onRetry: () -> Unit
) {
    Text(
        text = when (throwable) {
            is DisconnectException -> stringResource(R.string.text_network_disconnect)
            else -> stringResource(R.string.text_refresh_failed)
        },
        modifier = modifier
            .clickable { onRetry() },
        style = MaterialTheme.typography.bodyMedium
    )
}
