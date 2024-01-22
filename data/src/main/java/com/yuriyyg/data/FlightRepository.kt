package com.yuriyyg.data

import com.yuriyyg.entities.SearchResponse
import com.yuriyyg.network.api.ApiService
import javax.inject.Inject


interface FlightRepositoryInterface {
    suspend fun getFlights(): SearchResponse?
}

class FlightRepository @Inject constructor(private val apiService: ApiService):FlightRepositoryInterface {
    override suspend fun getFlights(): SearchResponse? {
        return apiService.getFlights()
    }

}