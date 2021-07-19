package com.example.exoplayer.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Repository {

    val retrofit = Retrofit.Builder()
        .baseUrl(URL.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}