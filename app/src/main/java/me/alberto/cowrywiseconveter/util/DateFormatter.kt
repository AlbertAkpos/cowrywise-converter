package me.alberto.cowrywiseconveter.util

import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {
    fun getDaysAgo(daysAgo: Int): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        return simpleDateFormat.format(calendar.time)
    }

    fun getCurrentDate(): String {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        return simpleDateFormat.format(Date())
    }
}