package com.example.sdui.network.service

import com.example.sdui.network.models.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun fetchComponentsFromApi(@Url url: String): ApiResponse
}



