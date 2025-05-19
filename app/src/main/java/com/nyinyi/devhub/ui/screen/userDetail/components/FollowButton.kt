package com.nyinyi.devhub.ui.screen.userDetail.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nyinyi.devhub.ui.theme.Primary

@Composable
fun FollowButton(
    count: Int,
    label: String,
    modifier: Modifier = Modifier,
    onClickWebView: () -> Unit = {}
) {
    FilledTonalButton(
        onClick = onClickWebView,
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        colors = ButtonDefaults.filledTonalButtonColors(
            containerColor = Primary.copy(alpha = 0.1f),
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        )
    ) {
        Text(
            text = "$count \n $label",
            style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                color = Primary
            ),
        )
    }
}