package com.jv23.echojournal.presentation.core.utils


import com.jv23.echojournal.R
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.time.DurationUnit
import kotlin.time.toDuration

fun formatLocalDateTimeToHourMinute(value: LocalDateTime): String {
    return String.format(Locale.getDefault(), "%02d:%02d", value.hour, value.minute)
}

fun getDisplayTextByDate(value: LocalDate): String {
    val now = LocalDate.now()
    val yesterday = now.dayOfYear - 1
    val formatter = DateTimeFormatter.ofPattern("EEEE, MMM d")
    val formatterWithYear = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy")

    return when {
        now.dayOfYear == value.dayOfYear -> R.string.today.toString()
        yesterday == value.dayOfYear -> R.string.yesterday.toString()
        else -> {
            if (now.year != value.year) {
                formatterWithYear.format(value)
            } else {
                formatter.format(value)
            }
        }
    }
}

fun formatSecondsToHourMinuteSecond(value: Long): String {
    val totalTimeDuration = value.toDuration(DurationUnit.SECONDS)
    val hours = totalTimeDuration.getHours()
    val minutes = totalTimeDuration.getRemainingMinutes()
    val seconds = totalTimeDuration.getRemainingSeconds()

    val hourString = if (hours > 0) {
        hours.toString()
    } else {
        null
    }
    val minuteString = String.format(Locale.getDefault(), "%02d", minutes)
    val secondString = String.format(Locale.getDefault(), "%02d", seconds)

    val timeString = listOfNotNull(hourString, minuteString, secondString)
    return timeString.joinToString(":")

    }