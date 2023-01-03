package com.weatherreportapp.weatherinfo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.weatherreportapp.databinding.ActivityMainBinding
import com.weatherreportapp.weatherinfo.adapter.RecyclerViewAdapter
import com.weatherreportapp.weatherinfo.model.Days
import com.weatherreportapp.weatherinfo.model.WeatherInfoResponse
import com.weatherreportapp.weatherinfo.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initViewModel()
    }

    private fun initView() {
        var linearLayoutManager= LinearLayoutManager(
            applicationContext, // Context
            RecyclerView.VERTICAL, // Orientation
            false // Reverse layout
        )
        binding.recyclerView.layoutManager = linearLayoutManager
        recyclerViewAdapter = RecyclerViewAdapter()
        binding.recyclerView.adapter = recyclerViewAdapter
    }

    private fun initViewModel() {
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getWeatherInfoObserver().observe(this, Observer<WeatherInfoResponse> {
            if (it != null) {
                val sunRise = it.city.sunrise
                val sunSet = it.city.sunset
                recyclerViewAdapter.setUpdatedData(sunRise, sunSet, it.list as ArrayList<Days>)
            } else {
                Toast.makeText(this, "Error in getting data", Toast.LENGTH_LONG).show()
            }

        })
        viewModel.getWeatherInfoApi()
    }
}