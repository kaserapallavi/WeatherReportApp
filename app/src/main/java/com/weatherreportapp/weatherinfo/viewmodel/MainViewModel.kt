package com.weatherreportapp.weatherinfo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weatherreportapp.BuildConfig
import com.weatherreportapp.network.RetrofitInstance
import com.weatherreportapp.network.RetrofitServices
import com.weatherreportapp.weatherinfo.model.WeatherInfoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var weatherInfoLiveData: MutableLiveData<WeatherInfoResponse>

    init {
        weatherInfoLiveData = MutableLiveData()
    }

    fun getWeatherInfoObserver() : MutableLiveData<WeatherInfoResponse> {
        return weatherInfoLiveData
    }

    fun getWeatherInfoApi() {
        viewModelScope.launch(Dispatchers.IO) {
            val retrofitInstance = RetrofitInstance.service?.create(RetrofitServices::class.java)
            val response = retrofitInstance?.callApiForWeatherInfo("Navi Mumbai",
                    BuildConfig.APP_ID, 50)
            weatherInfoLiveData.postValue(response)
        }
    }
}