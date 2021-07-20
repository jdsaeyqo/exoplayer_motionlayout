package com.example.exoplayer.service

import com.example.exoplayer.dto.VideoDto
import retrofit2.Response
import retrofit2.http.GET

interface VideoService {

    @GET("/v3/c3790c68-0d03-4ecf-bf70-6fc8835ae66a")
    suspend fun listVideos() : Response<VideoDto>
}