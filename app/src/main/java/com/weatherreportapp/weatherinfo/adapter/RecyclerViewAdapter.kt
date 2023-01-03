package com.weatherreportapp.weatherinfo.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.weatherreportapp.R
import com.weatherreportapp.weatherinfo.model.Days
import com.weatherreportapp.weatherinfo.view.WeatherData
import java.time.Instant


class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    private var days = ArrayList<Days>()
    private var sunSet = 0
    private var sunRise = 0


    fun setUpdatedData(sunRise: Int, sunSet: Int, days: ArrayList<Days>) {
        this.sunSet = sunSet
        this.sunRise = sunRise
        this.days = days
        notifyDataSetChanged()

    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val weatherImg = view.findViewById<ImageView>(R.id.weatherImg)
        val weatherTypeTxt = view.findViewById<TextView>(R.id.weatherTypeTxt)
        val tempTxt = view.findViewById<TextView>(R.id.tempTxt)
        val sunriseTxt = view.findViewById<TextView>(R.id.sunriseTxt)
        val sunsetTxt = view.findViewById<TextView>(R.id.sunsetTxt)
        val timeTxt = view.findViewById<TextView>(R.id.timeTxt)

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(sunRise: Int, sunSet: Int, data: Days) {
            val weatherData = WeatherData.fromJson(data)
            tempTxt.text = weatherData?.getmTemperature().toString()
            weatherTypeTxt.text = weatherData?.getmWeatherType().toString()
            timeTxt.text = data.dt_txt.toString()
            val sunrise: Long = sunRise.toLong()
            val sunset: Long = sunSet.toLong()
            sunriseTxt.text = "Sunrise : ${weatherData?.ConvertUTCtoCurrentDate(Instant.ofEpochSecond(sunrise).toString())}"
            sunsetTxt.text = "Sunset : ${weatherData?.ConvertUTCtoCurrentDate(Instant.ofEpochSecond(sunset).toString())}"


            if (weatherData?.micon == "clear") {
                Picasso.get()
                    .load(R.drawable.clear)
                    .into(weatherImg)
            } else if (weatherData?.micon == "cloud") {
                Picasso.get()
                    .load(R.drawable.cloud)
                    .into(weatherImg)
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_item, parent, false)

        return MyViewHolder(view)


    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(sunRise, sunSet, days[position])
    }

    override fun getItemCount(): Int {
        return days.size
    }
}