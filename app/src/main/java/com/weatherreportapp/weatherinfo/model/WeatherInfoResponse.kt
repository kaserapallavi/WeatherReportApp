package com.weatherreportapp.weatherinfo.model

data class WeatherInfoResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Days>,
    val message: Int
)