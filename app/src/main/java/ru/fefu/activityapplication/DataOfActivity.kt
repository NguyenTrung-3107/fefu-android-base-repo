package ru.fefu.activityapplication

import java.time.LocalDateTime

data class ActivityData(
    val distance: String,
    val activityType: String,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
)