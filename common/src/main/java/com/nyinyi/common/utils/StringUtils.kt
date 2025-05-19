package com.nyinyi.common.utils

fun formatCount(count: Int): String {
    return when {
        count >= 1000 -> String.format("%.1fK", count / 1000.0)
        else -> count.toString()
    }
}