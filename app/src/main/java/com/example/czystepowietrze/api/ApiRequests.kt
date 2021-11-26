package com.example.czystepowietrze.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRequests {

    @GET("pjp-api/rest/station/findAll")
    suspend fun getAllStations(): Response<List<AirStationJasonItem>>

    @GET("pjp-api/rest/station/sensors/{id}")
    suspend fun getStationDetails(
        @Path("id") id: String
    ): Response<List<AirStationDetailsJsonItem>>

    @GET("pjp-api/rest/data/getData/{paramId}")
    suspend fun getParamDetails(
        @Path("paramId") paramId: String
    ): Response<ParamDetailsJson>

}