package ru.fefu.activityapplication.models

import java.time.LocalDateTime

data class DataOfActivity(
    val distance: String,
    val activityType: String,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
) {
}