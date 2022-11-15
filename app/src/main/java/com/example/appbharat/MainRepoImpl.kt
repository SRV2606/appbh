package com.example.appbharat

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepoImpl @Inject constructor(private val mainService: MainService) : MainRepository {

    override suspend fun getMemes(): ServerMemes? {
        return withContext(Dispatchers.IO) {
            val response = mainService.getMemes()
            Log.d("SHAW_TAG", "getMemes: " + response.body())
            if (response.isSuccessful && response.body() != null) {
                return@withContext response.body() as ServerMemes
            }
            return@withContext null

        }
    }

}