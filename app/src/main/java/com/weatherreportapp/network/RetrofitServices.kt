package com.weatherreportapp.network

import com.weatherreportapp.weatherinfo.model.WeatherInfoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {
    @GET("forecast")
    suspend fun callApiForWeatherInfo(
        @Query("q") city: String,
        @Query("appid") appid: String,
        @Query("cnt") cnt: Int
    ): WeatherInfoResponse
}