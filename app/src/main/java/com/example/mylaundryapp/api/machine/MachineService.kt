package com.example.mylaundryapp.api.machine

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

interface MachineService {
    @GET("/fetch-machine")
    fun fetchMachine(): Call<List<MachineModel>>

    @GET("/filter-machine")
    fun fetchFilterMachine(
        @Query(value="classes", encoded=true) classes: Boolean?,
        @Query(value="type", encoded=true) type: Boolean?
    ): Call<List<MachineModel>>

    @PUT("/update-status-machine")
    fun putMachine(
        @Query(value="id", encoded=true) sl_id: Int?, @Body updateData : MachineModelUpdate
    ): Call<MachineModelUpdate>
}