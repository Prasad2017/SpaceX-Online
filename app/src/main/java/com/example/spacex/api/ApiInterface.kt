package com.example.spacex.api

import com.example.spacex.model.RocketsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("v4/rockets")
    fun getRocketList(): Call<List<RocketsResponse>>

    @GET("v4/rockets/{id}")
    fun getRocketDetails(@Path("id") id: String): Call<RocketsResponse>

}