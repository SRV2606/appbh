package com.example.appbharat

import retrofit2.Response
import retrofit2.http.GET

interface MainService {


    @GET("/get_memes")
    suspend fun getMemes(): Response<ServerMemes>
}