package com.example.appbharat

interface MainRepository {


    suspend fun getMemes(): ServerMemes?

}