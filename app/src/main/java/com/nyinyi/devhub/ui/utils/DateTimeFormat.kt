package com.nyinyi.devhub.ui.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun formatDateToAgo(dateString: String): String {
    try {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        val parsedDate = sdf.parse(dateString) ?: return "Unknown time"

        val currentDate = Date()

        val diffInMillis = currentDate.time - parsedDate.time

        val diffInSeconds = diffInMillis / 1000

        val secondsInMinute = 60
        val secondsInHour = secondsInMinute * 60
        val secondsInDay = secondsInHour * 24
        val secondsInMonth = secondsInDay * 30
        val secondsInYear = secondsInDay * 365

        return when {
            diffInSeconds < secondsInMinute -> "just now"
            diffInSeconds < secondsInHour -> {
                val minutes = diffInSeconds / secondsInMinute
                "$minutes minute${if (minutes > 1) "s" else ""} ago"
            }

            diffInSeconds < secondsInDay -> {
                val hours = diffInSeconds / secondsInHour
                "$hours hour${if (hours > 1) "s" else ""} ago"
            }

            diffInSeconds < secondsInMonth -> {
                val days = diffInSeconds / secondsInDay
                "$days day${if (days > 1) "s" else ""} ago"
            }

            diffInSeconds < secondsInYear -> {
                val months = diffInSeconds / secondsInMonth
                "$months month${if (months > 1) "s" else ""} ago"
            }

            else -> {
                val years = diffInSeconds / secondsInYear
                "$years year${if (years > 1) "s" else ""} ago"
            }
        }
    } catch (e: Exception) {
        return "Unknown time"
    }
}
