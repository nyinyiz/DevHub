package com.nyinyi.devhub.ui.utils

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun getLanguageColor(language: String): Color {
    return when (language.lowercase()) {
        "kotlin" -> Color(0xFF7F52FF)
        "java" -> Color(0xFFB07219)
        "javascript", "js" -> Color(0xFFF1E05A)
        "python" -> Color(0xFF3572A5)
        "swift" -> Color(0xFFFFAC45)
        "c#", "csharp" -> Color(0xFF178600)
        "c++" -> Color(0xFF00599C)
        "go" -> Color(0xFF00ADD8)
        "typescript", "ts" -> Color(0xFF2B7489)
        "ruby" -> Color(0xFF701516)
        else -> MaterialTheme.colorScheme.primary
    }
}
