package com.nanorus.articles.model.data.utils

import java.text.SimpleDateFormat
import java.util.*

class DateUtils {
    companion object {
        fun getDate(millis: Long?, dateFormat: String): String? {
            millis?.let { milliSeconds ->
                val formatter = SimpleDateFormat(dateFormat, Locale.ENGLISH)
                val calendar: Calendar = Calendar.getInstance()
                calendar.timeInMillis = milliSeconds
                return formatter.format(calendar.time)
            } ?: run {
                return null
            }
        }
    }
}