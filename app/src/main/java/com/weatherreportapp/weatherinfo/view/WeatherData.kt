package com.weatherreportapp.weatherinfo.view

import android.annotation.SuppressLint
import android.util.Log
import com.weatherreportapp.weatherinfo.model.Days
import org.json.JSONException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class WeatherData {
    private var mTemperature: String? = null
    var micon: String? = null
    private var mWeatherType: String? = null
    private var mCondition = 0
    var mSunRise : String? = null

    fun getmTemperature(): String {
        return "$mTemperature°C"
    }

    fun getmWeatherType(): String? {
        return mWeatherType
    }

    fun ConvertUTCtoCurrentDate(dateStr: String?): String? {
        @SuppressLint("SimpleDateFormat") val spf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        spf.setTimeZone(TimeZone.getTimeZone("UTC"))
        var formatedTime: String? = null
        try {
            val date: Date? = dateStr?.let { spf.parse(it) }
            val cal: Calendar = Calendar.getInstance()
            val tz: TimeZone = cal.getTimeZone()
            Log.d("Time zone", "=$tz")
            spf.setTimeZone(tz)
            @SuppressLint("SimpleDateFormat") val format = SimpleDateFormat("HH:mm aa")
            assert(date != null)
            formatedTime = format.format(date)
        } catch (e: ParseException) {
            Log.d("msg_time", java.lang.String.valueOf(e))
            e.printStackTrace()
        }
        return formatedTime
    }


    companion object {
        fun fromJson(list: Days): WeatherData? {
            return try {
                val weatherD = WeatherData()
                weatherD.mCondition =
                    list.weather[0].id
                weatherD.mWeatherType =
                    list.weather[0].main
                weatherD.micon = updateWeatherIcon(weatherD.mCondition)
                val tempResult = list.main.temp - 273.15
                val roundedValue = Math.rint(tempResult).toInt()
                weatherD.mTemperature = Integer.toString(roundedValue)
                weatherD
            } catch (e: JSONException) {
                e.printStackTrace()
                null
            }
        }


        private fun updateWeatherIcon(condition: Int): String {
            if (condition >= 0 && condition <= 300) {
                return "thunderstrom1"
            } else if (condition >= 300 && condition <= 500) {
                return "lightrain"
            } else if (condition >= 500 && condition <= 600) {
                return "shower"
            } else if (condition >= 600 && condition <= 700) {
                return "snow1"
            } else if (condition >= 701 && condition <= 771) {
                return "clear"
            } else if (condition >= 772 && condition <= 800) {
                return "clear"
            } else if (condition >= 801 && condition <= 804) {
                return "cloud"
            } else if (condition >= 900 && condition <= 902) {
                return "thunderstrom2"
            }
            if (condition == 903) {
                return "snow1"
            }
            if (condition == 904) {
                return "sunny"
            }
            return if (condition >= 905 && condition <= 1000) {
                "thunderstrom2"
            } else "dunno"
        }
    }
}
