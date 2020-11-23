package me.alberto.cowrywiseconveter.util

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class XAxisValueFormatter(private val dateList: List<String>) : ValueFormatter() {
        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            var position = Math.round(value)
            val sdf = SimpleDateFormat("MMM dd", Locale.ENGLISH)

            if (value > 1 && value < 2) {
                position = 0
            } else if (value > 2 && value < 3) {
                position = 1
            } else if (value > 3 && value < 4) {
                position = 2
            } else if (value > 4 && value <= 5) {
                position = 3
            }
            return if (position < dateList.size) sdf.format(
                Date(
                    getDateInMilliSeconds(
                        dateList.get(position),
                        "yyyy-MM-dd"
                    )
                )
            ) else ""
        }

        private fun getDateInMilliSeconds(dateString: String, format: String): Long {
            val sdf = SimpleDateFormat(format, Locale.US)
            var timeInMilliseconds: Long = 1
            try {
                val mDate = sdf.parse(dateString)
                timeInMilliseconds = mDate.time
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return timeInMilliseconds
        }

    }