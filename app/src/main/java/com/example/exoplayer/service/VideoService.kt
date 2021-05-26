package com.example.exoplayer.service

import com.example.exoplayer.dto.VideoDto
import retrofit2.Call
import retrofit2.http.GET

interface VideoService {

    @GET("/v3/c3790c68-0d03-4ecf-bf70-6fc8835ae66a")
    fun listVideos() : Call<VideoDto>
}