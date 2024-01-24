package com.yuriyyg.network.api

import com.yuriyyg.entities.model.SearchResponse
import retrofit2.http.GET

interface ApiService {

    @GET("flights")
    suspend fun getFlights(): SearchResponse?
}