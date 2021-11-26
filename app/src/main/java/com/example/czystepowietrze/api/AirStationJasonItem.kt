package com.example.czystepowietrze.api

data class AirStationJasonItem(
    val addressStreet: String,
    val city: City,
    val gegrLat: String,
    val gegrLon: String,
    val id: Int,
    val stationName: String
)