package com.weatherreportapp.network

import com.weatherreportapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    var mHttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    var mOkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(mHttpLoggingInterceptor)
        .build()

    var mRetrofit: Retrofit? = null


    val service: Retrofit?
        get() {
            if (mRetrofit == null) {
                mRetrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(mOkHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return mRetrofit
        }


}