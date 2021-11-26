package com.example.czystepowietrze.Repository

import com.example.czystepowietrze.api.AirStationDetailsJsonItem
import com.example.czystepowietrze.api.RetrofitInstance
import com.example.czystepowietrze.api.AirStationJasonItem
import com.example.czystepowietrze.api.ParamDetailsJson
import retrofit2.Response

class RetrofitRepository {

    suspend fun getAllStations(): Response<List<AirStationJasonItem>>{
        return RetrofitInstance.api.getAllStations()
    }

    suspend fun getStationDetails(id: String): Response<List<AirStationDetailsJsonItem>>{
        return RetrofitInstance.api.getStationDetails(id)
    }

    suspend fun getParamDetails(paramId: String): Response<ParamDetailsJson>{
        return RetrofitInstance.api.getParamDetails(paramId)
    }

}