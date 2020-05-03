package com.concrete.concretenosofa.repository

import android.content.Context
import com.concrete.concretenosofa.R
import com.concrete.concretenosofa.models.WelcomeInfo
import com.concrete.concretenosofa.utils.*
import java.util.*

class WelcomeInfoServices(private val context: Context, private val calendar: Calendar) {

    fun getWelcomeInfo() =
        WelcomeInfo(getSalutation(), getLogStringDate(), getBackgroundColor())

    private fun getSalutation() =
        when {
            calendar.get(Calendar.HOUR_OF_DAY) in 5..12 -> {
                context.getString(R.string.home_salutation_morning)
            }
            calendar.get(Calendar.HOUR_OF_DAY) in 12..18 -> {
                context.getString(R.string.home_salutation_evening)
            }
            else -> {
                context.getString(R.string.home_salutation_night)
            }
        }

    private fun getBackgroundColor() =
        when {
            calendar.get(Calendar.HOUR_OF_DAY) in 5..18 -> {
                R.color.colorBackgroundDay
            }
            else -> {
                R.color.colorBackgroundNight
            }
        }

    private fun getLogStringDate() =
        "${calendar.get(Calendar.DAY_OF_MONTH)} de ${calendar.get(Calendar.MONTH).getStringMonth()} de ${calendar.get(
            Calendar.YEAR
        )}"

    private fun Int.getStringMonth() =
        when (this) {
            Calendar.FEBRUARY -> context.getString(R.string.moth_feb)
            Calendar.MARCH -> context.getString(R.string.moth_mar)
            Calendar.APRIL -> context.getString(R.string.moth_apr)
            Calendar.MAY -> context.getString(R.string.moth_may)
            Calendar.JUNE -> context.getString(R.string.moth_jun)
            Calendar.JULY -> context.getString(R.string.moth_jul)
            Calendar.AUGUST -> context.getString(R.string.moth_ago)
            Calendar.SEPTEMBER -> context.getString(R.string.moth_sep)
            Calendar.OCTOBER -> context.getString(R.string.moth_oct)
            Calendar.NOVEMBER -> context.getString(R.string.moth_nov)
            Calendar.DECEMBER -> context.getString(R.string.moth_dec)
            else -> context.getString(R.string.moth_jan)
        }

    fun getIconWeather(iconCode: String) =
        when (iconCode) {
            CLEAR_SKY_DAY_01D -> R.drawable.ic_01d
            CLEAR_SKY_NIGHT_01N -> R.drawable.ic_01n
            FEW_CLOUDS_DAY_02D -> R.drawable.ic_02d
            FEW_CLOUDS_NIGHT_02N -> R.drawable.ic_02n
            SCATTERED_CLOUDS_DAY_3D -> R.drawable.ic_03d
            SCATTERED_CLOUDS_NIGHT_3N -> R.drawable.ic_03n
            BROKEN_CLOUDS_DAY_4D -> R.drawable.ic_03d
            BROKEN_CLOUDS_NIGHT_4N -> R.drawable.ic_03n
            SHOWER_RAIN_DAY_9D -> R.drawable.ic_09d
            SHOWER_RAIN_NIGHT_9N -> R.drawable.ic_09n
            RAIN_DAY_10D -> R.drawable.ic_10d
            RAIN_NIGHT_10N -> R.drawable.ic_10n
            THUNDERSTORM_DAY_11D -> R.drawable.ic_11d
            THUNDERSTORM_NIGHT_11N -> R.drawable.ic_11n
            else -> R.drawable.ic_face_emoji
        }


}